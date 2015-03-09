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

package org.eclipse.che.ide.util;

/** Utility class for common Exception related operations. */
public class ExceptionUtils {
    public static final int MAX_CAUSE = 10;

    public static String getStackTraceAsString(Throwable e) {
        return getThrowableAsString(e, "\n", "\t");
    }

    public static String getThrowableAsString(Throwable e, String newline, String indent) {
        if (e == null) {
            return "";
        }
        // For each cause, print the requested number of entries of its stack
        // trace, being careful to avoid getting stuck in an infinite loop.
        StringBuffer s = new StringBuffer(newline);
        Throwable currentCause = e;
        String causedBy = "";

        int causeCounter = 0;
        for (; causeCounter < MAX_CAUSE && currentCause != null; causeCounter++) {
            s.append(causedBy);
            causedBy = newline + "Caused by: "; // after 1st, all say "caused by"
            s.append(currentCause.getClass().getName());
            s.append(": ");
            s.append(currentCause.getMessage());
            StackTraceElement[] stackElems = currentCause.getStackTrace();
            if (stackElems != null) {
                for (int i = 0; i < stackElems.length; ++i) {
                    s.append(newline);
                    s.append(indent);
                    s.append("at ");
                    s.append(stackElems[i].toString());
                }
            }

            currentCause = currentCause.getCause();
        }
        if (causeCounter >= MAX_CAUSE) {
            s.append(newline);
            s.append(newline);
            s.append("Exceeded the maximum number of causes.");
        }

        return s.toString();
    }
}
