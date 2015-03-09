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

package org.eclipse.che.ide.collections;

/** String Set interface. */
public interface StringSet {

    /** Callback to support iterating through the keys of this set. */
    public interface IterationCallback {
        void onIteration(String key);
    }

    boolean contains(String key);

    Array<String> getKeys();

    boolean isEmpty();

    void iterate(IterationCallback callback);

    void add(String key);

    void addAll(Array<String> keys);

    /**
     * Removes the item with the given key.
     *
     * @param key
     *         key of the item to be removed from this set, if present
     * @return <tt>true</tt> if this set contained the specified element
     */
    boolean remove(String key);

    void clear();
}
