package org.grails.orm.hibernate

import grails.persistence.Entity

import org.hibernate.FlushMode

import org.junit.Test

import static junit.framework.Assert.*

/**
 * @author Graeme Rocher
 * @since 1.1
 */
class CustomValidatorAndDynamicFinderTests extends AbstractGrailsHibernateTests {

    // test for GRAILS-4981
    @Test
    void testCustomValidatorWithFinder() {
        session.setFlushMode(FlushMode.AUTO)

        def foo = new CustomValidatorAndDynamicFinderFoo(name: 'partner1')
        assertNotNull foo.save()
        def partner = new CustomValidatorAndDynamicFinderFoo(name: 'partner2')
        assertNotNull partner.save()
        foo.partner = partner
        assertNotNull foo.save()
    }

    @Override
    protected getDomainClasses() {
        [CustomValidatorAndDynamicFinderFoo]
    }
}

@Entity
class CustomValidatorAndDynamicFinderFoo {
    Long id
    Long version

    String name

    CustomValidatorAndDynamicFinderFoo partner

    static constraints = {
        name validator: { name, foo ->
            CustomValidatorAndDynamicFinderFoo similarFoo = CustomValidatorAndDynamicFinderFoo.findByNameIlike(name)
            if (similarFoo && similarFoo.id != foo.id) return ['similar']
        }
    }
}
