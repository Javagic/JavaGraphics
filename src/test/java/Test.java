import main.utils.VectorUtils;

import static junit.framework.TestCase.assertEquals;


public class Test {
    @org.junit.Test
    public void multiplication(){
        assertEquals(6.0,VectorUtils.sum(new double[]{1,2,3}));
    }

}
