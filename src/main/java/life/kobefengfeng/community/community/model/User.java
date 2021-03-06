package life.kobefengfeng.community.community.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.NAME
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.TOKEN
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_CREATE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_MOD
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private Long gmtMod;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.BIO
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.AVATAR_URL
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.TYPE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ACCOUNT_ID
     *
     * @return the value of USER.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ACCOUNT_ID
     *
     * @param accountId the value for USER.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.NAME
     *
     * @return the value of USER.NAME
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.NAME
     *
     * @param name the value for USER.NAME
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.TOKEN
     *
     * @return the value of USER.TOKEN
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.TOKEN
     *
     * @param token the value for USER.TOKEN
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_CREATE
     *
     * @return the value of USER.GMT_CREATE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_CREATE
     *
     * @param gmtCreate the value for USER.GMT_CREATE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_MOD
     *
     * @return the value of USER.GMT_MOD
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public Long getGmtMod() {
        return gmtMod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_MOD
     *
     * @param gmtMod the value for USER.GMT_MOD
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setGmtMod(Long gmtMod) {
        this.gmtMod = gmtMod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.BIO
     *
     * @return the value of USER.BIO
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.BIO
     *
     * @param bio the value for USER.BIO
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.AVATAR_URL
     *
     * @return the value of USER.AVATAR_URL
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.AVATAR_URL
     *
     * @param avatarUrl the value for USER.AVATAR_URL
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.TYPE
     *
     * @return the value of USER.TYPE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.TYPE
     *
     * @param type the value for USER.TYPE
     *
     * @mbg.generated Sat Jul 10 14:23:54 CST 2021
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}