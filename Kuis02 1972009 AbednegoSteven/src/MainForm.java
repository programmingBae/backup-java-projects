import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    private JPanel rootPanel;
    private JTable table1;
    private JTextField id;
    private JTextField name;
    private JTextField credits;
    private JCheckBox checkBox1;
    private JComboBox semesterBox;
    private JComboBox curriculumBox;
    private JButton addCurriculumButton;
    private JButton saveButton;
    private JButton resetButton;
    private JButton updateButton;
    private JButton deleteButton;
    private CourseDaoImpl courseDao;
    private CurriculumDaoImpl curriculumDao;
    private List<Curriculum> curriculumList;
    private List<Course> courseList;
    private DefaultComboBoxModel<Curriculum> curriculumDefaultComboBoxModel;
    private DefaultComboBoxModel courseDefaultComboBoxModel;
    private CourseTableModel courseTableModel;
    private Course selectedCourse;

    public MainForm() {
        curriculumDao =new CurriculumDaoImpl();
        courseDao = new CourseDaoImpl();
        curriculumList = new ArrayList<>();
        courseList = new ArrayList<>();

        try {
            curriculumList.addAll(curriculumDao.fetchAll());
            courseList.addAll(courseDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        curriculumDefaultComboBoxModel = new DefaultComboBoxModel(curriculumList.toArray(new Curriculum[0]));
        curriculumBox.setModel(curriculumDefaultComboBoxModel);


        courseTableModel = new CourseTableModel(courseList);
        table1.setModel(courseTableModel);
        table1.setAutoCreateRowSorter(true);
        addCurriculumButton.addActionListener(e -> {
            String newDepartment = JOptionPane.showInputDialog(rootPanel,"New Curriculum Name");
            if (newDepartment!=null && !newDepartment.trim().isEmpty()){
                Curriculum curriculum = new Curriculum();
                curriculum.setName(newDepartment);
                try {
                    if(curriculumDao.addData(curriculum)==1){
                        curriculumList.clear();
                        curriculumList.addAll(curriculumDao.fetchAll());
                        curriculumDefaultComboBoxModel.removeAllElements();
                        curriculumDefaultComboBoxModel.addAll(curriculumList);

                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        saveButton.addActionListener(e -> {
            if(id.getText().trim().isEmpty() || name.getText().trim().isEmpty() ||
                    credits.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(rootPanel,"Please fill all field","Error",JOptionPane.ERROR_MESSAGE);
            } else{
                Course course = new Course();
                course.setId(id.getText());
                course.setName(name.getText());
                course.setCredits(Integer.parseInt(credits.getText()));
                course.setLabSession(checkBox1.isSelected());
                course.setSemester((String)semesterBox.getSelectedItem());
                course.setCurriculum((Curriculum) curriculumBox.getSelectedItem());
                try {
                    if (courseDao.addData(course)==1){
                       courseList.clear();
                        courseList.addAll(courseDao.fetchAll());
                        courseTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        table1.getSelectionModel().addListSelectionListener(e -> {
            if(!table1.getSelectionModel().isSelectionEmpty()){
                int selectedIndex = table1.convertRowIndexToModel(table1.getSelectedRow());
                selectedCourse = courseList.get(selectedIndex);
                if(selectedCourse!=null){
                    id.setText(selectedCourse.getId());
                    name.setText(selectedCourse.getName());
                    credits.setText(String.valueOf(selectedCourse.getCredits()));
                    checkBox1.setSelected(true);
                    semesterBox.setSelectedItem(selectedCourse.getSemester());
                    curriculumBox.setSelectedItem(selectedCourse.getCurriculum());
                    saveButton.setEnabled(false);
                    updateButton.setEnabled(true);
                }
            }
        });
        updateButton.addActionListener(e -> {
            if(id.getText().trim().isEmpty() || name.getText().trim().isEmpty() ||
                    credits.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(rootPanel,"Please fill all field","Error",JOptionPane.ERROR_MESSAGE);
            } else{
                selectedCourse.setId(id.getText());
                selectedCourse.setName(name.getText());
                selectedCourse.setCredits(Integer.parseInt(credits.getText()));
                selectedCourse.setLabSession(checkBox1.isSelected());
                selectedCourse.setSemester(String.valueOf(semesterBox.getSelectedItem()));
                selectedCourse.setCurriculum((Curriculum) curriculumBox.getSelectedItem());
                try {
                    if (courseDao.updateData(selectedCourse)==1){
                        courseList.clear();
                        courseList.addAll(courseDao.fetchAll());
                        courseTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        resetButton.addActionListener(e -> {
            clearAndReset();
        });
        deleteButton.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null,
                    "Are you sure want to delete?", "Select an Option...",JOptionPane.YES_NO_CANCEL_OPTION);
            if (input == JOptionPane.YES_OPTION) {
                courseList.remove(selectedCourse);
                try {
                    if (courseDao.deleteData(selectedCourse) == 1) {
                        courseList.clear();
                        courseList.addAll(courseDao.fetchAll());
                        courseTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
            else{

            }
        });
    }
    private void clearAndReset(){
        id.setText("");
        name.setText("");
        credits.setText("");
        id.setEnabled(true);
        saveButton.setEnabled(true);
        updateButton.setEnabled(true);
        table1.clearSelection();
        selectedCourse = null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static class CourseTableModel extends AbstractTableModel{
        private List<Course>courseList;
        private final String[] COLUMNS = {"ID","NAME","CREDITS","SEMESTER","CURRICULUM"};

        public CourseTableModel(List<Course> courseList) {
            this.courseList = courseList;
        }
        @Override
        public int getRowCount() {
            return courseList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> courseList.get(rowIndex).getId();
                case 1 -> courseList.get(rowIndex).getName();
                case 2 -> courseList.get(rowIndex).getCredits();
                case 3 -> courseList.get(rowIndex).getSemester();
                case 4 -> courseList.get(rowIndex).getCurriculum().getName();
                default -> "";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0,columnIndex)!=null){
                return getValueAt(0,columnIndex).getClass();
            }
            return Object.class;
        }

    }
}
