package deckedout_test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import deckedout.model.ObjectDO;

public class ObjectDOTest {
    @Test
    public void validateSettersAndGetters() throws Exception{


        PojoClass activityPojo = PojoClassFactory.getPojoClass(ObjectDO.class);

        Validator validator = ValidatorBuilder.create()
                // Lets make sure that we have a getter and a setter for every field defined.
                .with(new SetterMustExistRule()).with(new GetterMustExistRule())

                // Lets also validate that they are behaving as expected
                .with(new SetterTester()).with(new GetterTester()).build();

        // Start the Test
        validator.validate(activityPojo);
    }
    /*
    @Test
    public void validateIsValid() throws Exception {
        Lieblingsfarbe lieblingsfarbe_null = new Lieblingsfarbe(null);
        assertEquals("isNotValid_null", false, lieblingsfarbe_null.isValid());
        Lieblingsfarbe lieblingsfarbe_empty = new Lieblingsfarbe("");
        assertEquals("isNotValid_empty", false, lieblingsfarbe_empty.isValid());
        Lieblingsfarbe lieblingsfarbe = new Lieblingsfarbe("blau");
        assertEquals("isValid", true, lieblingsfarbe.isValid());
    }
  */
    
    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void validateIsValid() throws Exception {
        
    	// Test with two-String constructor
        ObjectDO object_null = new ObjectDO(null, null);
        assertEquals(false, object_null.locationIsValid());
        assertEquals(false, object_null.nameIsValid());
        
        ObjectDO object_empty = new ObjectDO("","");
        assertEquals(false, object_empty.locationIsValid());
        assertEquals(false, object_empty.nameIsValid());
        
        ObjectDO object = new ObjectDO("Schlüssel", "Schrank");
        assertEquals(true, object.locationIsValid());
        assertEquals(true, object.nameIsValid());

        // Test with one-String constructor
        ObjectDO object_null_one = new ObjectDO(null);
        assertEquals(false, object_null_one.nameIsValid());
        
        ObjectDO object_empty_one = new ObjectDO("");
        assertEquals(false, object_empty_one.nameIsValid());
        
        ObjectDO object_one = new ObjectDO("Schlüssel");
        assertEquals(true, object_one.nameIsValid());
        
        //Test toStringDO
        assertEquals("Schlüssel:Schrank", object.toStringDO());        
        
        //Test fromString
        assertEquals(new ObjectDO("Handy","Esstisch"), ObjectDO.fromString("Handy:Esstisch"));
        //assertEquals(null, ObjectDO.fromString(":"));
        
        //Test equals
        ObjectDO o = new ObjectDO("Handy", "Esstisch");
        ObjectDO o2 = new ObjectDO("Handy", "Schrank");
        ObjectDO o3 = new ObjectDO("Schlüssel", "Esstisch");
        
        assertEquals(true, new ObjectDO("Handy","Esstisch").equals(o));
        assertEquals(false, o.equals(o2));
        assertEquals(false, o.equals(o3));
        assertEquals(true , o.equals(o));
        assertEquals(false, o.equals(null));
        Integer testnInt = new  Integer(1);
        assertEquals(false, o.equals(testnInt));
        
        
        //Test hashcode        
        assertEquals(o.hashCode(), o.hashCode());
        o.setLocation(null);
        assertEquals(o.hashCode(), o.hashCode());        
        o = new ObjectDO(null, "Tisch");
        assertEquals(o.hashCode(), o.hashCode());
        assertEquals(false, o.hashCode() == new ObjectDO("Handy", "Esstisch").hashCode());
        
    }
}
