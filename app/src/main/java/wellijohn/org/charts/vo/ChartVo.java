package wellijohn.org.charts.vo;

import java.util.List;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/17-11:35
 * @email:
 * @desc:
 */
public class ChartVo {

    /**
     * commissionTrendLine : {"chartType":"LINE","itemList":[{"dataList":[82.8,68.2,31.5,42.3,8,2.3,24.7,9.9],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[48.4,4.8,4,39.7,65.3,2.3,34.5,55.1],"itemColor":"#6ED","itemName":"保养"},{"dataList":[49.9,31.8,18.4,17.7,25.6,72.5,99.2,47.2],"itemColor":"#6ED","itemName":"维修"},{"dataList":[39.3,88.8,49.8,46.3,98.9,30.5,18.1,41.3],"itemColor":"#FD1E10","itemName":"保养"},{"dataList":[75.3,98.6,36.1,87,10.2,33.1,29.4,64.3],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[46.9,58.9,72.5,91.4,20.4,45.1,88.3,48.7],"itemColor":"#6ED","itemName":"保养"},{"dataList":[76,50.5,24.6,19.6,9.8,61.8,92.7,5.2],"itemColor":"#FD1E10","itemName":"汽车美容"},{"dataList":[38.7,90.3,45.3,57.3,61.7,55.3,82,72.8],"itemColor":"#6ED","itemName":"保养"}],"ratioItem":{"dataList":[56.4,80.3,1.7,37.3,28.8,29.6,53.3,17.9],"itemColor":"#FD1E10","itemName":"保养"},"style":"H","title":"维修","totalItem":{"dataList":[25,67.4,39.8,25.1,61,69.2,10.6,22],"itemColor":"#6ED","itemName":"保养"},"xAxisTime":["10/01-10/12","08/10-08/17","08/10-08/17","08/01-08-07","08/10-08/17","08/01-08-07","10/01-10/12","08/01-08-07"]}
     * commissionTrendRatioLine : {"chartType":"LINE","itemList":[{"dataList":[87.7,0.6,15.8,15.3,35.3,95.7,27.6,70.8],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[85.1,38.4,61.1,77,57.7,62,23,9.4],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[36.8,1.8,73.6,85.6,99.7,14.7,63.6,9.6],"itemColor":"#FD1E10","itemName":"汽车美容"},{"dataList":[95.3,46.6,66.5,41.6,8.9,62.7,58.6,30.9],"itemColor":"#6ED","itemName":"洗车"},{"dataList":[66.3,39,3.1,21.5,55.1,73,29.4,65.1],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[43,19,9.6,85,1.3,76.3,50.2,8],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[19.1,4.5,70.3,1,16.6,89.2,35.5,4.7],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[18.7,5.6,7.6,85.7,51.4,67.5,18.3,32.7],"itemColor":"#FD1E10","itemName":"维修"}],"ratioItem":{"dataList":[3.3,80.6,46.7,56.1,15,95,81.2,56.9],"itemColor":"#6ED","itemName":"保养"},"style":"V","title":"保养","totalItem":{"dataList":[58.1,58.9,94.5,66.8,8,66.8,75.6,17.1],"itemColor":"#FD1E10","itemName":"保养"},"xAxisTime":["10/01-10/12","08/10-08/17","08/10-08/17","10/01-10/12","08/01-08-07","10/01-10/12","10/01-10/12","08/01-08-07"]}
     * commissionTypePie : {"chartType":"PIE","dataList":[{"color":"#DD2","name":"洗车","ratio":75,"value":40.4},{"color":"#FFF","name":"维修","ratio":7.1,"value":97},{"color":"#FFF","name":"洗车","ratio":82,"value":72.1},{"color":"#FFF","name":"维修","ratio":41.7,"value":62.2},{"color":"#DD2","name":"维修","ratio":89,"value":67.1},{"color":"#DD2","name":"维修","ratio":40.5,"value":94.6},{"color":"#FFF","name":"保养","ratio":54.3,"value":8.3},{"color":"#DD2","name":"汽车美容","ratio":5.1,"value":70}],"title":"维修"}
     * total : {"basicName":"保养","headItemList":[{"name":"汽车美容","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"10%"},{"name":"维修","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name ":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"10%"}],"itemList":[{"dataList":[{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"保养","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"保养","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"15712"},{"name":"维修","valueString":"1014.0"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"洗车"},{"dataList":[{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"洗车"},{"dataList":[{"name":"洗车","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"保养"},{"dataList":[{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"10%"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"洗车","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"保养","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"维修","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"}],"itemIcon":"http://www.image/1.png","itemName":"洗车","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"汽车美容","valueString":"1014.0"},{"name":"洗车","valueString":"10%"},{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"保养","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"维修","valueString":"15712"},{"name":"汽车美容","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"},{"name":"汽车美容","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"15712"},{"name":"洗车","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"汽车美容"}],"totalAmount":74.7}
     */

    private CommissionTrendLineBean commissionTrendLine;
    private CommissionTrendRatioLineBean commissionTrendRatioLine;
    private CommissionTypePieBean commissionTypePie;
    private TotalBean total;

    public CommissionTrendLineBean getCommissionTrendLine() {
        return commissionTrendLine;
    }

    public void setCommissionTrendLine(CommissionTrendLineBean commissionTrendLine) {
        this.commissionTrendLine = commissionTrendLine;
    }

    public CommissionTrendRatioLineBean getCommissionTrendRatioLine() {
        return commissionTrendRatioLine;
    }

    public void setCommissionTrendRatioLine(CommissionTrendRatioLineBean commissionTrendRatioLine) {
        this.commissionTrendRatioLine = commissionTrendRatioLine;
    }

    public CommissionTypePieBean getCommissionTypePie() {
        return commissionTypePie;
    }

    public void setCommissionTypePie(CommissionTypePieBean commissionTypePie) {
        this.commissionTypePie = commissionTypePie;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public static class CommissionTrendLineBean {
        /**
         * chartType : LINE
         * itemList : [{"dataList":[82.8,68.2,31.5,42.3,8,2.3,24.7,9.9],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[48.4,4.8,4,39.7,65.3,2.3,34.5,55.1],"itemColor":"#6ED","itemName":"保养"},{"dataList":[49.9,31.8,18.4,17.7,25.6,72.5,99.2,47.2],"itemColor":"#6ED","itemName":"维修"},{"dataList":[39.3,88.8,49.8,46.3,98.9,30.5,18.1,41.3],"itemColor":"#FD1E10","itemName":"保养"},{"dataList":[75.3,98.6,36.1,87,10.2,33.1,29.4,64.3],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[46.9,58.9,72.5,91.4,20.4,45.1,88.3,48.7],"itemColor":"#6ED","itemName":"保养"},{"dataList":[76,50.5,24.6,19.6,9.8,61.8,92.7,5.2],"itemColor":"#FD1E10","itemName":"汽车美容"},{"dataList":[38.7,90.3,45.3,57.3,61.7,55.3,82,72.8],"itemColor":"#6ED","itemName":"保养"}]
         * ratioItem : {"dataList":[56.4,80.3,1.7,37.3,28.8,29.6,53.3,17.9],"itemColor":"#FD1E10","itemName":"保养"}
         * style : H
         * title : 维修
         * totalItem : {"dataList":[25,67.4,39.8,25.1,61,69.2,10.6,22],"itemColor":"#6ED","itemName":"保养"}
         * xAxisTime : ["10/01-10/12","08/10-08/17","08/10-08/17","08/01-08-07","08/10-08/17","08/01-08-07","10/01-10/12","08/01-08-07"]
         */

        private String chartType;
        private RatioItemBean ratioItem;
        private String style;
        private String title;
        private TotalItemBean totalItem;
        private List<ItemListBean> itemList;
        private List<String> xAxisTime;

        public String getChartType() {
            return chartType;
        }

        public void setChartType(String chartType) {
            this.chartType = chartType;
        }

        public RatioItemBean getRatioItem() {
            return ratioItem;
        }

        public void setRatioItem(RatioItemBean ratioItem) {
            this.ratioItem = ratioItem;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public TotalItemBean getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(TotalItemBean totalItem) {
            this.totalItem = totalItem;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public List<String> getXAxisTime() {
            return xAxisTime;
        }

        public void setXAxisTime(List<String> xAxisTime) {
            this.xAxisTime = xAxisTime;
        }

        public static class RatioItemBean {
            /**
             * dataList : [56.4,80.3,1.7,37.3,28.8,29.6,53.3,17.9]
             * itemColor : #FD1E10
             * itemName : 保养
             */

            private String itemColor;
            private String itemName;
            private List<Double> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Double> getDataList() {
                return dataList;
            }

            public void setDataList(List<Double> dataList) {
                this.dataList = dataList;
            }
        }

        public static class TotalItemBean {
            /**
             * dataList : [25,67.4,39.8,25.1,61,69.2,10.6,22]
             * itemColor : #6ED
             * itemName : 保养
             */

            private String itemColor;
            private String itemName;
            private List<Integer> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Integer> getDataList() {
                return dataList;
            }

            public void setDataList(List<Integer> dataList) {
                this.dataList = dataList;
            }
        }

        public static class ItemListBean {
            /**
             * dataList : [82.8,68.2,31.5,42.3,8,2.3,24.7,9.9]
             * itemColor : #FD1E10
             * itemName : 维修
             */

            private String itemColor;
            private String itemName;
            private List<Double> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Double> getDataList() {
                return dataList;
            }

            public void setDataList(List<Double> dataList) {
                this.dataList = dataList;
            }
        }
    }

    public static class CommissionTrendRatioLineBean {
        /**
         * chartType : LINE
         * itemList : [{"dataList":[87.7,0.6,15.8,15.3,35.3,95.7,27.6,70.8],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[85.1,38.4,61.1,77,57.7,62,23,9.4],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[36.8,1.8,73.6,85.6,99.7,14.7,63.6,9.6],"itemColor":"#FD1E10","itemName":"汽车美容"},{"dataList":[95.3,46.6,66.5,41.6,8.9,62.7,58.6,30.9],"itemColor":"#6ED","itemName":"洗车"},{"dataList":[66.3,39,3.1,21.5,55.1,73,29.4,65.1],"itemColor":"#FD1E10","itemName":"维修"},{"dataList":[43,19,9.6,85,1.3,76.3,50.2,8],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[19.1,4.5,70.3,1,16.6,89.2,35.5,4.7],"itemColor":"#6ED","itemName":"汽车美容"},{"dataList":[18.7,5.6,7.6,85.7,51.4,67.5,18.3,32.7],"itemColor":"#FD1E10","itemName":"维修"}]
         * ratioItem : {"dataList":[3.3,80.6,46.7,56.1,15,95,81.2,56.9],"itemColor":"#6ED","itemName":"保养"}
         * style : V
         * title : 保养
         * totalItem : {"dataList":[58.1,58.9,94.5,66.8,8,66.8,75.6,17.1],"itemColor":"#FD1E10","itemName":"保养"}
         * xAxisTime : ["10/01-10/12","08/10-08/17","08/10-08/17","10/01-10/12","08/01-08-07","10/01-10/12","10/01-10/12","08/01-08-07"]
         */

        private String chartType;
        private RatioItemBeanX ratioItem;
        private String style;
        private String title;
        private TotalItemBeanX totalItem;
        private List<ItemListBeanX> itemList;
        private List<String> xAxisTime;

        public String getChartType() {
            return chartType;
        }

        public void setChartType(String chartType) {
            this.chartType = chartType;
        }

        public RatioItemBeanX getRatioItem() {
            return ratioItem;
        }

        public void setRatioItem(RatioItemBeanX ratioItem) {
            this.ratioItem = ratioItem;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public TotalItemBeanX getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(TotalItemBeanX totalItem) {
            this.totalItem = totalItem;
        }

        public List<ItemListBeanX> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBeanX> itemList) {
            this.itemList = itemList;
        }

        public List<String> getXAxisTime() {
            return xAxisTime;
        }

        public void setXAxisTime(List<String> xAxisTime) {
            this.xAxisTime = xAxisTime;
        }

        public static class RatioItemBeanX {
            /**
             * dataList : [3.3,80.6,46.7,56.1,15,95,81.2,56.9]
             * itemColor : #6ED
             * itemName : 保养
             */

            private String itemColor;
            private String itemName;
            private List<Double> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Double> getDataList() {
                return dataList;
            }

            public void setDataList(List<Double> dataList) {
                this.dataList = dataList;
            }
        }

        public static class TotalItemBeanX {
            /**
             * dataList : [58.1,58.9,94.5,66.8,8,66.8,75.6,17.1]
             * itemColor : #FD1E10
             * itemName : 保养
             */

            private String itemColor;
            private String itemName;
            private List<Double> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Double> getDataList() {
                return dataList;
            }

            public void setDataList(List<Double> dataList) {
                this.dataList = dataList;
            }
        }

        public static class ItemListBeanX {
            /**
             * dataList : [87.7,0.6,15.8,15.3,35.3,95.7,27.6,70.8]
             * itemColor : #FD1E10
             * itemName : 维修
             */

            private String itemColor;
            private String itemName;
            private List<Double> dataList;

            public String getItemColor() {
                return itemColor;
            }

            public void setItemColor(String itemColor) {
                this.itemColor = itemColor;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public List<Double> getDataList() {
                return dataList;
            }

            public void setDataList(List<Double> dataList) {
                this.dataList = dataList;
            }
        }
    }

    public static class CommissionTypePieBean {
        /**
         * chartType : PIE
         * dataList : [{"color":"#DD2","name":"洗车","ratio":75,"value":40.4},{"color":"#FFF","name":"维修","ratio":7.1,"value":97},{"color":"#FFF","name":"洗车","ratio":82,"value":72.1},{"color":"#FFF","name":"维修","ratio":41.7,"value":62.2},{"color":"#DD2","name":"维修","ratio":89,"value":67.1},{"color":"#DD2","name":"维修","ratio":40.5,"value":94.6},{"color":"#FFF","name":"保养","ratio":54.3,"value":8.3},{"color":"#DD2","name":"汽车美容","ratio":5.1,"value":70}]
         * title : 维修
         */

        private String chartType;
        private String title;
        private List<DataListBean> dataList;

        public String getChartType() {
            return chartType;
        }

        public void setChartType(String chartType) {
            this.chartType = chartType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * color : #DD2
             * name : 洗车
             * ratio : 75
             * value : 40.4
             */

            private String color;
            private String name;
            private int ratio;
            private double value;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRatio() {
                return ratio;
            }

            public void setRatio(int ratio) {
                this.ratio = ratio;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }
    }

    public static class TotalBean {
        /**
         * basicName : 保养
         * headItemList : [{"name":"汽车美容","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"10%"},{"name":"维修","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name ":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"10%"}]
         * itemList : [{"dataList":[{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"保养","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"保养","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"15712"},{"name":"维修","valueString":"1014.0"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"洗车"},{"dataList":[{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"},{"name":"维修","valueString":"15712"},{"name":"洗车","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"洗车"},{"dataList":[{"name":"洗车","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"维修","valueString":"1014.0"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"保养"},{"dataList":[{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"汽车美容","valueString":"10%"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"洗车","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"保养","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"维修","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"}],"itemIcon":"http://www.image/1.png","itemName":"洗车","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"汽车美容","valueString":"1014.0"},{"name":"洗车","valueString":"10%"},{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"10%"},{"name":"保养","valueString":"15712"},{"name":"汽车美容","valueString":"15712"}],"itemIcon":"http://www.image/1.png","itemName":"保养","itemTotalAmount":"汽车美容"},{"dataList":[{"name":"维修","valueString":"15712"},{"name":"汽车美容","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"},{"name":"汽车美容","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"15712"},{"name":"洗车","valueString":"1014.0"},{"name":"汽车美容","valueString":"10%"}],"itemIcon":"http://www.image/1.png","itemName":"维修","itemTotalAmount":"汽车美容"}]
         * totalAmount : 74.7
         */

        private String basicName;
        private double totalAmount;
        private List<HeadItemListBean> headItemList;
        private List<ItemListBeanXX> itemList;

        public String getBasicName() {
            return basicName;
        }

        public void setBasicName(String basicName) {
            this.basicName = basicName;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<HeadItemListBean> getHeadItemList() {
            return headItemList;
        }

        public void setHeadItemList(List<HeadItemListBean> headItemList) {
            this.headItemList = headItemList;
        }

        public List<ItemListBeanXX> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBeanXX> itemList) {
            this.itemList = itemList;
        }

        public static class HeadItemListBean {
            /**
             * name : 汽车美容
             * valueString : 10%
             * name  : 洗车
             */

            private String name;
            private String valueString;
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValueString() {
                return valueString;
            }

            public void setValueString(String valueString) {
                this.valueString = valueString;
            }

        }

        public static class ItemListBeanXX {
            /**
             * dataList : [{"name":"汽车美容","valueString":"10%"},{"name":"洗车","valueString":"15712"},{"name":"保养","valueString":"15712"},{"name":"保养","valueString":"1014.0"},{"name":"保养","valueString":"10%"},{"name":"汽车美容","valueString":"1014.0"},{"name":"维修","valueString":"15712"},{"name":"保养","valueString":"15712"}]
             * itemIcon : http://www.image/1.png
             * itemName : 保养
             * itemTotalAmount : 汽车美容
             */

            private String itemIcon;
            private String itemName;
            private String itemTotalAmount;
            private List<DataListBeanX> dataList;

            public String getItemIcon() {
                return itemIcon;
            }

            public void setItemIcon(String itemIcon) {
                this.itemIcon = itemIcon;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getItemTotalAmount() {
                return itemTotalAmount;
            }

            public void setItemTotalAmount(String itemTotalAmount) {
                this.itemTotalAmount = itemTotalAmount;
            }

            public List<DataListBeanX> getDataList() {
                return dataList;
            }

            public void setDataList(List<DataListBeanX> dataList) {
                this.dataList = dataList;
            }

            public static class DataListBeanX {
                /**
                 * name : 汽车美容
                 * valueString : 10%
                 */

                private String name;
                private String valueString;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValueString() {
                    return valueString;
                }

                public void setValueString(String valueString) {
                    this.valueString = valueString;
                }
            }
        }
    }
}
