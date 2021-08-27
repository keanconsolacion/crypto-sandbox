package example.keanconsolacion.bottomnavigationactivitysample.Model;

public class Helper {

    public static double limitDouble(double value){
        String stringValue = String.valueOf(value);
        if(stringValue.charAt(5) == '.'){
            return Double.parseDouble(stringValue.substring(0,7));
        }else{
            return Double.parseDouble(stringValue.substring(0,7));
        }
    }
}
