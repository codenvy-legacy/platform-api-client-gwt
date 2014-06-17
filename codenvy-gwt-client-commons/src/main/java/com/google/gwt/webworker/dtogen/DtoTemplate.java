// Copyright 2012 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gwt.webworker.dtogen;

import com.codenvy.ide.dto.shared.RoutingType;
import com.google.common.base.Preconditions;
import com.google.gwt.webworker.client.messages.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base template for the generated output file that contains all the DTOs.
 * <p/>
 * Note that we generate client and server DTOs in separate runs of the generator.
 * <p/>
 */
public class DtoTemplate {
    public static class MalformedDtoInterfaceException extends RuntimeException {
        public MalformedDtoInterfaceException(String msg) {
            super(msg);
        }
    }

    // We keep a whitelist of allowed non-DTO generic types.
    static final Set<Class< ? >> jreWhitelist  = new HashSet<Class< ? >>(
                                                                         Arrays.asList(new Class< ? >[]{String.class, Integer.class,
            Double.class, Float.class, Boolean.class                     }));

    private final List<DtoImpl>  dtoInterfaces = new ArrayList<DtoImpl>();

    private final String         packageName;

    private final String         className;

    private final String         apiHash;

//    /**
//     * @return whether or not the specified interface implements {@link com.codenvy.ide.dtogen.shared.ClientToServerDto}.
//     */
//    static boolean implementsClientToServerDto(Class< ? > i) {
//        return implementsInterface(i, Message.class);
//    }

//    /**
//     * @return whether or not the specified interface implements {@link com.codenvy.ide.dtogen.shared.ServerToClientDto}.
//     */
//    static boolean implementsServerToClientDto(Class< ? > i) {
//        return implementsInterface(i, ServerToClientDto.class);
//    }

    /**
     * Walks the superinterface hierarchy to determine if a Class implements some target interface transitively.
     */
    static boolean implementsInterface(Class< ? > i, Class< ? > target) {
        if (i.equals(target)) {
            return true;
        }

        boolean rtn = false;
        Class< ? >[] superInterfaces = i.getInterfaces();
        for (Class< ? > superInterface : superInterfaces) {
            rtn = rtn || implementsInterface(superInterface, target);
        }
        return rtn;
    }

    /**
     * @return whether or not the specified interface implements {@link Message}.
     */
    private static boolean implementsRoutableDto(Class< ? > i) {
        return implementsInterface(i, Message.class);
    }

    /**
     * Constructor.
     * 
     * @param packageName The name of the package for the outer DTO class.
     * @param className The name of the outer DTO class.
     */
    DtoTemplate(String packageName, String className, String apiHash) {
        this.packageName = packageName;
        this.className = className;
        this.apiHash = apiHash;
    }

    /**
     * Adds an interface to the DtoTemplate for code generation.
     * 
     * @param i
     */
    public void addInterface(Class< ? > i) {
        getDtoInterfaces().add(createDtoImplTemplate(i));
    }

    /** @return the dtoInterfaces */
    public List<DtoImpl> getDtoInterfaces() {
        return dtoInterfaces;
    }

    /**
     * Returns the source code for a class that contains all the DTO impls for any intefaces that were added via the
     * {@link #addInterface(Class)} method.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        emitPreamble(builder);
        emitClientFrontendApiVersion(builder);
        emitDtos(builder);
        emitPostamble(builder);
        return builder.toString();
    }

    /**
     * Tests whether or not a given class is a part of our dto jar, and thus will eventually have a generated Impl that is serializable
     * (thus allowing it to be a generic type).
     */
    boolean isDtoInterface(Class< ? > potentialDto) {
        for (DtoImpl dto : dtoInterfaces) {
            if (dto.getDtoInterface().equals(potentialDto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Will initialize the routing ID to be RoutableDto.INVALID_TYPE if it is not routable. This is a small abuse of the intent of that
     * value, but it allows us to simply omit it from the routing type enumeration later.
     * 
     * @param i the super interface type
     * @return a new DtoServerTemplate or a new DtoClientTemplate depending on isServerImpl.
     */
    private DtoImpl createDtoImplTemplate(Class< ? > i) {
        int routingId = implementsRoutableDto(i) ? getRoutingId(i) : Message.NON_ROUTABLE_TYPE;
        return new DtoImplClientTemplate(this, routingId, i);
    }

    private void emitDtos(StringBuilder builder) {
        for (DtoImpl dto : getDtoInterfaces()) {
            builder.append(dto.serialize());
        }
    }

    private void emitPostamble(StringBuilder builder) {
        builder.append("\n}");
    }

    private void emitPreamble(StringBuilder builder) {
        builder.append("/*******************************************************************************\n");
        builder.append(" * Copyright (c) 2012-2014 Codenvy, S.A.\n");
        builder.append(" * All rights reserved. This program and the accompanying materials\n");
        builder.append(" * are made available under the terms of the Eclipse Public License v1.0\n");
        builder.append(" * which accompanies this distribution, and is available at\n");
        builder.append(" * http://www.eclipse.org/legal/epl-v10.html\n");
        builder.append(" *\n");
        builder.append(" * Contributors:\n");
        builder.append(" * Codenvy, S.A. - initial API and implementation\n");
        builder.append(" *******************************************************************************/\n\n\n");
        builder.append("// GENERATED SOURCE. DO NOT EDIT.\npackage ");
        builder.append(packageName);
        builder.append(";\n\n");

        builder.append("\n\n@SuppressWarnings({\"unchecked\", \"cast\"})\n");
        // Note that we always use fully qualified path names when referencing Types
        // so we need not add any import statements for anything.
        builder.append("public class ");
        builder.append(className);
        builder.append(" {\n\n");
        builder.append("  private  ");
        builder.append(className);
        builder.append("() {}\n");
    }

    /**
     * Emits a static variable that is the hash of all the classnames, methodnames, and return types to be used as a version hash between
     * client and server.
     */
    private void emitClientFrontendApiVersion(StringBuilder builder) {
        builder.append("\n  public static final String CLIENT_SERVER_PROTOCOL_HASH = \"");
        builder.append(getApiHash());
        builder.append("\";\n");
    }

    private String getApiHash() {
        return apiHash;
    }

    /**
     * Extracts the {@link com.codenvy.ide.dtogen.shared.RoutingType} annotation to derive the stable routing type.
     */
    private int getRoutingId(Class< ? > i) {
        RoutingType routingTypeAnnotation = i.getAnnotation(RoutingType.class);

        Preconditions.checkNotNull(routingTypeAnnotation,
                                   "RoutingType annotation must be specified for all subclasses of RoutableDto. " + i.getName());

        return routingTypeAnnotation.type();
    }
}
