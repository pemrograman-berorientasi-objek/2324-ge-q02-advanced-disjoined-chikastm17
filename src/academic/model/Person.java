package academic.model;
/**
 * 
 * @author 12S22023 Chika Situmorang
 *  @author 12S22004 Bethania Hasibuan
 */
public class Person {
    
        protected String id;
        protected String name;
    
        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id + "|" + name;
        }
}



