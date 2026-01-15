import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArticleAnalyzer {
    private ArrayList<String> stopWords;
    private ArrayList<String> headlines;
    private ArrayList<String> descriptions;
    private ArrayList<String> categories;
    
    public ArticleAnalyzer() {
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Stop words count: " + stopWords.size());
        headlines = new ArrayList<>();
        descriptions = new ArrayList<>();
        categories = new ArrayList<>();
        System.out.println("Articles count: " + headlines.size());
    }
    
    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> articleLines = FileOperator.getStringList("News_Category_Dataset_v3.json");
        for (String line : articleLines) {
            analyzer.parseJson(line);
        }
        
        System.out.println("Articles count after loading: " + analyzer.headlines.size());
        for (int i = 0; i < analyzer.descriptions.size(); i++) {
            String cleanedDescription = analyzer.removeStopWords(analyzer.descriptions.get(i));
            analyzer.descriptions.set(i, cleanedDescription);
        }
        for (int i = 0; i < analyzer.headlines.size(); i++) {
            System.out.println("Headline: " + analyzer.headlines.get(i));
            System.out.println("Description: " + analyzer.descriptions.get(i));
            System.out.println("---");
        }
    }
    
    public void parseJson(String jsonLine) {
        try {
            Pattern headlinePattern = Pattern.compile("\"headline\"\\s*:\\s*\"([^\"]+)\"");
            Matcher headlineMatcher = headlinePattern.matcher(jsonLine);
            Pattern descPattern = Pattern.compile("\"short_description\"\\s*:\\s*\"([^\"]+)\"");
            Matcher descMatcher = descPattern.matcher(jsonLine);
            Pattern categoryPattern = Pattern.compile("\"category\"\\s*:\\s*\"([^\"]+)\"");
            Matcher categoryMatcher = categoryPattern.matcher(jsonLine);
            
            String headline = "";
            String description = "";
            String category = "";
            
            if (headlineMatcher.find()) {
                headline = headlineMatcher.group(1);
            }
            
            if (descMatcher.find()) {
                description = descMatcher.group(1);
            }
            
            if (categoryMatcher.find()) {
                category = categoryMatcher.group(1);
            }
            addArticle(headline, description, category);
            
        } catch (Exception e) {
            System.err.println("Error parsing JSON line: " + e.getMessage());
        }
    }
    
    public void addArticle(String headline, String description, String category) {
        headlines.add(headline);
        descriptions.add(description);
        categories.add(category);
    }
    
    public void addStopWord(String word) {
        if (word != null && !word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
            stopWords.add(word.toLowerCase());
        }
    }
    
    public String removeStopWords(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        String result = text;
        String[] words = text.split("\\s+");
        StringBuilder cleanedText = new StringBuilder();
        
        for (String word : words) {
            String wordLower = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!stopWords.contains(wordLower) && !wordLower.isEmpty()) {
                if (cleanedText.length() > 0) {
                    cleanedText.append(" ");
                }
                cleanedText.append(word);
            }
        }
        
        result = cleanedText.toString();
        return result;
    }
}