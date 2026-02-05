import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 

    public ArticleAnalyzer(){
        stopWords=FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop Word count"+stopWords.size());
        articles=new ArrayList<>();
        System.out.println("Articles count"+articles.size());



    }
    public static void main(String[] args) {
        ArticleAnalyzer Kowin = new ArticleAnalyzer();
        ArrayList<String> sentimentLines = FileOperator.getStringList("sentiments.txt");
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        for (String line : sentimentLines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String word = matcher.group(1);  // Extract word
                Double value = Double.parseDouble(matcher.group(2)); // Extract decimal value
                
                words.add(word);
                values.add(value);
            }
        }
        for (int i = 0; i < words.size(); i++) {
            System.out.println("Word: " + words.get(i) + ", Value: " + values.get(i));
        }

    //    ArticleAnalyzer riano = new ArticleAnalyzer();
    //    ArrayList<String> lines= FileOperator.getStringList("data.txt");
    //     for(String line : lines){
    
    //    Article a=riano.parseJson(line);
    //    String clean = riano.removeStopWords(a.getDescription());
    //    a.setDescription(clean);
    //    System.out.println(a);
    //     riano.addArticle(a);
    //     }

       


    }

    public void addStopWord(String word){

    }

    public void addArticle(Article article){

    }

    public Article parseJson(String jsonLine){
 
        Article result;
        Pattern l = Pattern.compile("\"link\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher lm =l.matcher(jsonLine); //parameter - line of text
        String lt = lm.find() ? lm.group(1) : ""; //extract the destined part

        
        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher hm =l.matcher(jsonLine); //parameter - line of text
        String ht = lm.find() ? lm.group(1) : ""; //extract the destined part
        
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher cm =l.matcher(jsonLine); //parameter - line of text
        String ct = lm.find() ? lm.group(1) : ""; //extract the destined part

        Pattern d = Pattern.compile("\"short_description\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher dm =l.matcher(jsonLine); //parameter - line of text
        String dt = lm.find() ? lm.group(1) : ""; //extract the destined part


         Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher am =l.matcher(jsonLine); //parameter - line of text
        String at = lm.find() ? lm.group(1) : ""; //extract the destined part

         Pattern t = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher tm =l.matcher(jsonLine); //parameter - line of text
        String tt = lm.find() ? lm.group(1) : ""; //extract the destined part

        
        result=new Article(lt, ht, ct, dt, at, tt);

    return result;
}

 
    public String removeStopWords(String text){
        
        String result="";
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (!stopWords.contains(word)) {
                result += word + " ";
            }
        }
        return result.trim();
    } //remove stop words from Description


}