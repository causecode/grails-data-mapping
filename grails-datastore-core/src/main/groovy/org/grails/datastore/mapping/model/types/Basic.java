/* Copyright (C) 2011 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.datastore.mapping.model.types;

import java.beans.PropertyDescriptor;

import org.grails.datastore.mapping.engine.types.CustomTypeMarshaller;
import org.grails.datastore.mapping.model.MappingContext;
import org.grails.datastore.mapping.model.PersistentEntity;

/**
 * Models a basic collection type such as a list of Strings
 *
 * @author Graeme Rocher
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
public abstract class Basic extends Association {

    private CustomTypeMarshaller customTypeMarshaller;
    // Used to hold related type of collection, like String for a list of string.
    private Class<Object> collectionType;

    public Basic(PersistentEntity owner, MappingContext context,
            PropertyDescriptor descriptor) {
        super(owner, context, descriptor);
    }

    public Basic(PersistentEntity owner, MappingContext context, String name, Class type) {
        super(owner, context, name, type);
    }

    @Override
    public Association getInverseSide() {
        return null; // basic collection types have no inverse side
    }

    @Override
    public boolean isOwningSide() {
        return true;
    }

    @Override
    public void setOwningSide(boolean owningSide) {
        // noop
    }

    @Override
    public PersistentEntity getAssociatedEntity() {
        return null; // basic collection types have no associated entity
    }

    /**
     * @return The type converter for this custom type
     */
    @SuppressWarnings("rawtypes")
    public CustomTypeMarshaller getCustomTypeMarshaller() {
        return customTypeMarshaller;
    }

    public void setCustomTypeMarshaller(CustomTypeMarshaller customTypeMarshaller) {
        this.customTypeMarshaller = customTypeMarshaller;
    }

    public Class<Object> getCollectionType() {
        return collectionType;
    }

    public boolean isEnumTypeCollection() {
        return (collectionType != null && collectionType.isEnum());
    }

    public void setCollectionType(Class<Object> collectionType) {
        this.collectionType = collectionType;
    }
}
