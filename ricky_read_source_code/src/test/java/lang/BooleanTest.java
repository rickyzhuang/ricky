package lang;

import junit.framework.TestCase;

/**
 * Created by zhuangjiayin on 2019/6/9.
 */
public class BooleanTest extends TestCase {

    public void testBooleanValue(){
       Boolean  a= new Boolean(true);
       Boolean  b= true;
       Boolean  c= Boolean.TRUE;
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b==c);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    public void testChar(){

//        Character.Subset subset=new Character.Subset("11");// 类型可见 无法编译
//        Character.CharacterCache s=new Character.CharacterCache();//类型都不可见

    }


    public class  a {}

    private   class  b {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private class c extends b{

    }

}
