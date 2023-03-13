package test;

import Dao.TableHistoryDao;
import TableModel.Table;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;

public class TableDaoTest {

    @Test
    public void getEmptyHistoryTest() throws IOException, ClassNotFoundException {
        TableHistoryDao dao=new TableHistoryDao();
        ArrayList<Table> myTableHistory =new ArrayList<Table>();
        ArrayList<Table> tables= dao.getFullTableHistory();
        Assertions.assertEquals(myTableHistory.toString(),tables.toString());

    }

    @Test
    public void update_Get_FullHistoryTest() throws IOException, ClassNotFoundException {
        TableHistoryDao dao=new TableHistoryDao();
        Table t1=new Table();
        Table t2=new Table();
        Table t3=new Table();
        ArrayList<Table> myTableHistory =new ArrayList<Table>();
        myTableHistory.add(t1);
        myTableHistory.add(t2);
        myTableHistory.add(t3);

        dao.updateFullHistoryTables(myTableHistory);
        ArrayList<Table> TableHistory= dao.getFullTableHistory();


        Assertions.assertEquals(myTableHistory.get(0).getTableId(),TableHistory.get(0).getTableId());
        Assertions.assertEquals(myTableHistory.get(1).getTableId(),TableHistory.get(1).getTableId());
        Assertions.assertEquals(myTableHistory.get(2).getTableId(),TableHistory.get(2).getTableId());

    }

    @Test
    public void archiveTableTest() throws IOException, ClassNotFoundException {
        TableHistoryDao dao=new TableHistoryDao();
        Table t1=new Table();
        Table t2=new Table();
        Table t3=new Table();
        ArrayList<Table> myTableHistory =new ArrayList<Table>();
        myTableHistory.add(t1);
        myTableHistory.add(t2);
        myTableHistory.add(t3);

        dao.addTableToHistory(t1);
        dao.addTableToHistory(t2);
        dao.addTableToHistory(t3);
        ArrayList<Table> TableHistory= dao.getFullTableHistory();


        Assertions.assertEquals(myTableHistory.get(0).getTableId(),TableHistory.get(0).getTableId());
        Assertions.assertEquals(myTableHistory.get(1).getTableId(),TableHistory.get(1).getTableId());
        Assertions.assertEquals(myTableHistory.get(2).getTableId(),TableHistory.get(2).getTableId());

    }



}
