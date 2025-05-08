package uk.ac.ncl.team5project.entity;
/**
 * Class: Author
 * File: Author.java
 * Created on: 2025/5/8
 * Author: Menghui Yao
 *
 * Description:
 * <pre>
 *     Function: Represents an author entity with personal and professional details.
 *     Interface Description:
 *         - Calling Sequence:
 *                          getAuthorId/setAuthorId: Gets/Sets the unique identifier for the author.
 *                          getAuthorName/setAuthorName: Gets/Sets the name of the author.
 *                          getNationality/setNationality: Gets/Sets the nationality of the author.
 *                          getProfessional/setProfessional: Gets/Sets the professional title of the author.
 *                          getFamousWork/setFamousWork: Gets/Sets the famous work of the author.
 *                          getAuthorDescribe/setAuthorDescribe: Gets/Sets the description of the author.
 *                          toString: Returns a string representation of the author.
 *         - Argument Description:
 *                          authorId (Integer): Unique identifier for the author.
 *                          authorName (String): Full name of the author.
 *                          nationality (String): Country of origin of the author.
 *                          professional (String): Professional title or occupation.
 *                          famousWork (String): Most notable work by the author.
 *                          authorDescribe (String): Brief description about the author.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Menghui Yao
 *     Reviewer: Menghui Yao
 *     Review Date: 2025/5/8
 *     Modification Date: 2025/5/8
 *     Modification Description: Initial implementation of Author entity.
 * </pre>
 */
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