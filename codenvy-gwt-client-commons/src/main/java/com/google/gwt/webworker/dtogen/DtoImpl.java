/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.google.gwt.webworker.dtogen;

import com.codenvy.ide.collections.Array;
import com.codenvy.ide.collections.StringMap;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.gwt.webworker.client.messages.CompactJsonMessage;
import com.google.gwt.webworker.client.messages.SerializationIndex;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Abstract base class for the source generating template for a single DTO. */
abstract class DtoImpl {
    // If routingType is RoutableDto.INVALID_TYPE, then we simply exclude it
    // from our routing table.
    private final int routingType;

    private final Class<?> dtoInterface;

    private final DtoTemplate enclosingTemplate;

    private final boolean compactJson;

    final String implClassName;

    private final List<Method> dtoMethods;

    DtoImpl(DtoTemplate enclosingTemplate, int routingType, Class<?> dtoInterface) {
        this.enclosingTemplate = enclosingTemplate;
        this.routingType = routingType;
        this.dtoInterface = dtoInterface;
        this.implClassName = dtoInterface.getSimpleName() + "Impl";
        this.compactJson = DtoTemplate.implementsInterface(dtoInterface, CompactJsonMessage.class);
        this.dtoMethods = ImmutableList.copyOf(calcDtoMethods());
    }

    protected boolean isCompactJson() {
        return compactJson;
    }

    public Class<?> getDtoInterface() {
        return dtoInterface;
    }

    public DtoTemplate getEnclosingTemplate() {
        return enclosingTemplate;
    }

    public int getRoutingType() {
        return routingType;
    }

    protected String getFieldName(String methodName) {
        // TODO: Consier field name obfuscation for code savings on the
        // wire. For now just use a munging of the getter's name (strip get and
        // make first letter lower cased).
        String fieldName = methodName.replaceFirst("get", "");
        fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
        return fieldName;
    }

    protected String getImplClassName() {
        return implClassName;
    }

    protected String getSetterName(String fieldName) {
        return "set" + getCamelCaseName(fieldName);
    }

    protected String getListAdderName(String fieldName) {
        return "add" + getCamelCaseName(fieldName);
    }

    protected String getMapPutterName(String fieldName) {
        return "put" + getCamelCaseName(fieldName);
    }

    protected String getClearName(String fieldName) {
        return "clear" + getCamelCaseName(fieldName);
    }

    protected String getEnsureName(String fieldName) {
        return "ensure" + getCamelCaseName(fieldName);
    }

    protected String getCamelCaseName(String fieldName) {
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    /**
     * Our super interface may implement some other interface (or not). We need to
     * know because if it does then we need to directly extend said super
     * interfaces impl class.
     */
    protected Class<?> getSuperInterface() {
        Class<?>[] superInterfaces = dtoInterface.getInterfaces();
        return superInterfaces.length == 0 ? null : superInterfaces[0];
    }

    /**
     * We need not generate a field and method for any method present on a parent
     * interface that our interface may inherit from. We only care about the new
     * methods defined on our superInterface.
     */
    protected boolean ignoreMethod(Method method) {
        if (method == null) {
            return true;
        }

        // Look at any interfaces our superInterface implements.
        Class<?>[] superInterfaces = dtoInterface.getInterfaces();
        List<Method> methodsToExclude = new ArrayList<Method>();

        // Collect methods on parent interfaces
        for (Class<?> parent : superInterfaces) {
            for (Method m : parent.getMethods()) {
                methodsToExclude.add(m);
            }
        }

        for (Method m : methodsToExclude) {
            if (m.equals(method)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests whether or not a given generic type is allowed to be used as a
     * generic.
     */
    protected static boolean isWhitelisted(Class<?> genericType) {
        return DtoTemplate.jreWhitelist.contains(genericType);
    }

    /** Tests whether or not a given return type is a Array. */
    public static boolean isJsonArray(Class<?> returnType) {
        return returnType.equals(Array.class);
    }

    /** Tests whether or not a given return type is a Array. */
    public static boolean isJsonStringMap(Class<?> returnType) {
        return returnType.equals(StringMap.class);
    }

    /**
     * Expands the type and its first generic parameter (which can also have a
     * first generic parameter (...)).
     * <p/>
     * For example, Array&lt;StringMap&lt;Array&lt;SomeDto&gt;&gt;&gt;
     * would produce [Array, StringMap, Array, SomeDto].
     */
    public static List<Type> expandType(Type curType) {
        List<Type> types = new ArrayList<Type>();

        do {
            types.add(curType);

            if (curType instanceof ParameterizedType) {
                Type[] genericParamTypes = ((ParameterizedType)curType).getActualTypeArguments();
                if (genericParamTypes.length != 1) {
                    throw new IllegalStateException(
                            "Multiple type parameters are not supported" + "(neither are zero type parameters)");
                }

                Type genericParamType = genericParamTypes[0];
                if (genericParamType instanceof Class<?>) {
                    Class<?> genericParamTypeClass = (Class<?>)genericParamType;
                    if (isWhitelisted(genericParamTypeClass)) {
                        assert genericParamTypeClass.equals(
                                String.class) : "For JSON serialization there can be only strings or DTO types. " +
                                                "Please ping smok@ if you see this assert happening.";
                    }
                }

                curType = genericParamType;

            } else {
                if (curType instanceof Class) {
                    Class<?> clazz = (Class<?>)curType;
                    if (isJsonArray(clazz) || isJsonStringMap(clazz)) {
                        throw new DtoTemplate.MalformedDtoInterfaceException(
                                "Array and StringMap MUST have a generic type specified (and no... ? " + "doesn't cut it!).");
                    }
                }

                curType = null;
            }
        }
        while (curType != null);

        return types;
    }

    public static Class<?> getRawClass(Type type) {
        return (Class<?>)((type instanceof ParameterizedType) ? ((ParameterizedType)type).getRawType() : type);
    }

    String getRoutingTypeField() {
        return dtoInterface.getSimpleName().toUpperCase() + "_TYPE";
    }

    /**
     * Returns public methods specified in DTO interface.
     * <p/>
     * <p>For compact DTO (see {@link CompactJsonMessage}) methods are ordered
     * corresponding to {@link SerializationIndex} annotation.
     * <p/>
     * <p>Gaps in index sequence are filled with {@code null}s.
     */
    protected List<Method> getDtoMethods() {
        return dtoMethods;
    }

    private Method[] calcDtoMethods() {
        if (!compactJson) {
            return dtoInterface.getMethods();
        }

        Map<Integer, Method> methodsMap = new HashMap<Integer, Method>();
        int maxIndex = 0;
        for (Method method : dtoInterface.getMethods()) {
            if (method.getName().equals("getType")) {
                continue;
            }

            SerializationIndex serializationIndex = method.getAnnotation(SerializationIndex.class);
            Preconditions.checkNotNull(serializationIndex, "Serialization index is not specified for %s in %s",
                                       method.getName(), dtoInterface.getSimpleName());

            // "53" is the number of bits in JS integer.
            // This restriction will allow to add simple bit-field
            // "serialization-skipping-list" in the future.
            int index = serializationIndex.value();
            Preconditions.checkState(index > 0 && index <= 53, "Serialization index out of range [1..53] for %s in %s",
                                     method.getName(), dtoInterface.getSimpleName());

            Preconditions.checkState(!methodsMap.containsKey(index), "Duplicate serialization index for %s in %s",
                                     method.getName(), dtoInterface.getSimpleName());

            maxIndex = Math.max(index, maxIndex);
            methodsMap.put(index, method);
        }

        Method[] result = new Method[maxIndex];
        for (int index = 0; index < maxIndex; index++) {
            result[index] = methodsMap.get(index + 1);
        }

        return result;
    }

    protected boolean isLastMethod(Method method) {
        Preconditions.checkNotNull(method);
        return method == dtoMethods.get(dtoMethods.size() - 1);
    }

    /**
     * @return String representing the source definition for the DTO impl as an
     *         inner class.
     */
    abstract String serialize();
}
