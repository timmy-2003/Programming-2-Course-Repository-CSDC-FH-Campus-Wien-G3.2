package at.ac.fhcampuswien;
//Uses Builder Pattern
public class Article {
    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String description;
    private Source source;
    /*
    public static class ArticleBuilder{
        private String author;
        private String title;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
        private String description;
        private Source source;

        public ArticleBuilder(String author, String title, String url, String urlToImage,
                              String publishedAt, String content, String description, Source source){
            this.author = author;
            this.title = title;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
            this.content = content;
            this.description = description;
            this.source = source;
        }

        public ArticleBuilder(){}

        public Article build() {

            if(author == null || title == null){
                throw new IllegalStateException("Article is missing one or multiple attributes!");

            }
            return new Article(this);
        }

        public ArticleBuilder author(String value){
            author = value;
            return this;
        }

        public ArticleBuilder title(String value){
            title = value;
            return this;
        }

        public ArticleBuilder url(String value){
            url = value;
            return this;
        }

        public ArticleBuilder urlToImage(String value){
            urlToImage = value;
            return this;
        }

        public ArticleBuilder publishedAt(String value){
            publishedAt = value;
            return this;
        }

        public ArticleBuilder content(String value){
            content = value;
            return this;
        }

        public ArticleBuilder description(String value){
            description = value;
            return this;
        }

        public ArticleBuilder source(Source value){
            source = value;
            return this;
        }
    }
    */
    //CONSTRUCTOR
    public Article(String author, String title, String url, String urlToImage, String publishedAt, String content, String description, Source source) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.description = description;
        this.source = source;
    }

/*
    private Article(ArticleBuilder articleBuilder) {
        this.author = articleBuilder.author;
        this.title = articleBuilder.title;
        this.url = articleBuilder.url;
        this.urlToImage = articleBuilder.urlToImage;
        this.publishedAt = articleBuilder.publishedAt;
        this.content = articleBuilder.content;
        this.description = articleBuilder.description;
        this.source = articleBuilder.source;
    }

 */


    //CONSTRUCTOR
    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    //CONSTRUCTOR
    public Article(String author, String title, String desc) {
        this.author = author;
        this.title = title;
        this.description= desc;
    }

    //CONSTRUCTOR
    public Article(String author, String title, Source source) {
        this.author = author;
        this.title = title;
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLength()
    {
        return description.length();
    }

    public Source getSource() {
        return source;
    }

    public String getSourceName() {
        return source.getName();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return author and title of article formatted as a String
     */
    @Override
    public String toString() {
        return "Title: " + title + "\t" + "Author: " + author + "\t" +  "Description: " + description;
    }
}
