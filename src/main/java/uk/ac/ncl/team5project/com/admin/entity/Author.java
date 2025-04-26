package uk.ac.ncl.team5project.com.admin.entity;

public class Author {
    private Integer authorId;

    private String authorName;

    private String nationality;

    private String professional;

    private String famousWork;

    private String authorDescribe;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getFamousWork() {
        return famousWork;
    }

    public void setFamousWork(String famousWork) {
        this.famousWork = famousWork;
    }

    public String getAuthorDescribe() {
        return authorDescribe;
    }

    public void setAuthorDescribe(String authorDescribe) {
        this.authorDescribe = authorDescribe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName=").append(authorName);
        sb.append(", nationality=").append(nationality);
        sb.append(", professional=").append(professional);
        sb.append(", famousWork=").append(famousWork);
        sb.append(", authorDescribe=").append(authorDescribe);
        sb.append("]");
        return sb.toString();
    }
}