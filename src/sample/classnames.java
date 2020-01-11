package sample;

public class classnames {
    private String Classname;

    public classnames(String classname) {
        this.Classname = classname;
    }

    public String getClassname() {
        return Classname;
    }

    public void setClassname(String classname) {
        Classname = classname;
    }

    @Override
    public String toString() {
        return getClassname();
    }
}


//        public List<classnames> getAllRows() throws SQLException {
//        String sql1 = "SELECT * FROM cr6.class ORDER BY ID";
//        PreparedStatement prst2 = conn.prepareStatement(sql1);
//        ResultSet resset = prst2.executeQuery();
//        List<classnames> list1 = new ArrayList<>();
//        while (resset.next()) {
//            String classname = resset.getString("classname");
//            list1.add(new classnames(classname));
//        }
//        prst2.close();
//        return list1;