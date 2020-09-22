package music;

public class Musicgame {

    // id pk
    private String id;

    // ques 질문
    private String ques;

    // ans1 문답1
    private String ans1;

    // ans2 문답2
    private String ans2;

    // ans3 문답3
    private String ans3;

    // ans4 문답4
    private String ans4;

    // correct 정답
    private String correct;

    // name 이름
    private String name;

    // music_file 음악파일
    private String musicFile;

    // img_file 이미지파일
    private String imgFile;

    // use_yn 사용여부
    private String useYn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    // Musicgame 모델 복사
    public void CopyData(Musicgame param)
    {
        this.id = param.getId();
        this.ques = param.getQues();
        this.ans1 = param.getAns1();
        this.ans2 = param.getAns2();
        this.ans3 = param.getAns3();
        this.ans4 = param.getAns4();
        this.correct = param.getCorrect();
        this.name = param.getName();
        this.musicFile = param.getMusicFile();
        this.imgFile = param.getImgFile();
        this.useYn = param.getUseYn();
    }
}