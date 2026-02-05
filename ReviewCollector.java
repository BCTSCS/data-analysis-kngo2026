import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ReviewCollector {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    private ArrayList<String> words;
    private ArrayList<Double> values;
    public ReviewCollector(){
        reviewList=new ArrayList<>();
        productList=new ArrayList<>();
        words = new ArrayList<>();
        values = new ArrayList<>();
    }
    public void addReview(ProductReview review){
        reviewList.add(review);
    }
    public int getNumGoodReviews(String prodName){
        int count=0;
        for(ProductReview review : reviewList){
            if(review.getName().equals(prodName)){
                if(review.getReview().toLowerCase().contains("good") || review.getReview().toLowerCase().contains("excellent") || review.getReview().toLowerCase().contains("great")){
                    count++;
                }
            }
        }
        return count;
    }
     public void processSentiments(String filePath) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList(filePath);

        // Regex pattern to match word,decimal pairs
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");


        // Process each line
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1); // Extract the word
                Double value = Double.parseDouble(matcher.group(2)); // Extract the value

                // Add to instance variables
                words.add(word);
                values.add(value);

                // Print the result
                System.out.println(word + "   ----  " + value);
   
            }
        }
    }
        public static void main (String[] args){
            ReviewCollector rc=new ReviewCollector();
            rc.processSentiments("sentiments.txt");
        }
}
