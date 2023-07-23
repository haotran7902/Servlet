package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Account;
import entity.Book;
import entity.Category;
import entity.Product;

public class DAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	public List<Product> getAllProduct(){
		List<Product> list = new ArrayList<>();
		String query = "select * from tor.product";
		
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)));
			}
		} catch (Exception e) {}
		
		return list;
	}
	public List<Book> getAllBook(){
		List<Book> list = new ArrayList<>();
		String query = "select * from book";
		
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				int bookcode = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				String category = rs.getString(4);
				int approved = rs.getInt(5);
				list.add(new Book(bookcode, title, author, category, approved == 0 ? false : true));
			}
		} catch (Exception e) {}
		
		return list;
	}
	public List<Category> getAllCategory(){
		List<Category> list = new ArrayList<>();
		String query = "SELECT * FROM wish.category";
		
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(new Category(rs.getInt(1),
						rs.getString(2)));
			}
		} catch (Exception e) {}
		
		return list;
	}
	public List<Product> getProductByCID(String cid){
		List<Product> list = new ArrayList<>();
		String query = "select * from wish.product where cateID = ?";
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, cid);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)));
			}
		} catch (Exception e) {}
		
		return list;
	}
	public Product getProductByID(String id){
		String query = "select * from wish.product where id = ?";
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, id);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				return new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8));
			}
		} catch (Exception e) {}
		
		return null;
	}
	public List<Product> searchByName(String txtSearch){
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM wish.product\r\n"
				+ "where name like ?";
		try {
			connection = new DBContext().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1,"%" + txtSearch + "%");
			rs = statement.executeQuery();
			
			while(rs.next()) {
				list.add(new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)));
			}
		} catch (Exception e) {}
		
		return list;
	}
	public Product getLast() {
        String query = "select * from wish.product where id = (SELECT MAX(id) FROM wish.product)";
        try {
            connection = new DBContext().getConnection();//mo ket noi voi sql
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while(rs.next()) {
				return new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8));
			}
        } catch (Exception e) {
        }
        return null;
    }
	public Account login(String user, String pass) {
		String query = "SELECT * FROM wish.account where user = ? and pass = ?";
		try {
			connection = new DBContext().getConnection();//mo ket noi voi sql
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);
            rs = statement.executeQuery();
            while(rs.next()) {
            	return new Account(rs.getInt(1),
            			rs.getString(2),
            			rs.getString(3),
            			rs.getInt(4),
            			rs.getInt(5));
            }
		} catch (Exception e) {}
		
		return null;
	}
	public Account chechAccountExist(String user) {
		String query = "SELECT * FROM wish.account where user = ?";
		try {
			connection = new DBContext().getConnection();//mo ket noi voi sql
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            rs = statement.executeQuery();
            while(rs.next()) {
            	return new Account(rs.getInt(1),
            			rs.getString(2),
            			rs.getString(3),
            			rs.getInt(4),
            			rs.getInt(5));
            }
		} catch (Exception e) {}
		
		return null;
	}
	public void signUp(String user, String pass) {
		String query1 = "SELECT MAX(uID) FROM wish.account";
		String query2 = "INSERT INTO wish.account (`uID`, `user`, `pass`, `isSell`, `isAdmin`) "
				+ "VALUES (?, ?, ?, 0, 0);";
		int idMax = -1;
		try {
			connection = new DBContext().getConnection();//mo ket noi voi sql
			statement = connection.prepareStatement(query1);
            rs = statement.executeQuery();
            while(rs.next()) {
            	idMax = rs.getInt(1);
            }
			
            PreparedStatement statement = null;
        	ResultSet rs = null;
            statement = connection.prepareStatement(query2);
            statement.setInt(1, idMax + 1);
            statement.setString(2, user);
            statement.setString(3, pass);
            statement.executeUpdate();
		} catch (Exception e) {}
	}
	public static void main(String[] args) {
		DAO dao = new DAO();
		List<Product> list = dao.getAllProduct();
		System.out.println(list.size());
		for(Product p: list) {
			System.out.println(p);
		}
	}
}
