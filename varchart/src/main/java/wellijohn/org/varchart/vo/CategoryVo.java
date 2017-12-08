package wellijohn.org.varchart.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/15-16:13
 * @email:
 * @desc:
 */
public class CategoryVo {

    /**
     * 卡券类目
     */
    private String categoryName;

    /**
     * 每个卡券类目的值
     */
    private List<String> categoryValueList;

    public CategoryVo() {

        categoryValueList = new ArrayList<>();
        Random rand = new Random();
        categoryName = "卡" + rand.nextInt(4);
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
        categoryValueList.add(String.valueOf(rand.nextInt(13)));
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getCategoryValueList() {
        return categoryValueList;
    }

    public void setCategoryValueList(List<String> categoryValueList) {
        this.categoryValueList = categoryValueList;
    }
}
