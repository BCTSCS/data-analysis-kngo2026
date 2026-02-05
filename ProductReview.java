import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
public class ProductReview {
    private String name, review;
    
    public ProductReview(String pname, String preview){
        this.name=pname;
        this.review=preview;
    }
    public String getName(){
        return name;
    }
    public String getReview(){
        return review;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setReview(String review){
        this.review=review;
    }
    public static void main(String [] args){
        String regex = "Product:\\s*(?<product>.*?)\\s*-\\s*Review:\\s*(?<review>.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("product.txt");
        List<ProductReview> reviews = new ArrayList<>();
        while (matcher.find()) {
            String pName = matcher.group("product");
            String pReview = matcher.group("review");
            reviews.add(new ProductReview(pName, pReview));
        }
    }
}
