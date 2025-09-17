// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentResultSystem{
   private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "Swaru@55396";
   private static int loggedInStudentId = -1;
   private static Scanner scanner;
   private static Connection conn;

   public StudentResultSystem() {
   }

   public static void main(String[] var0) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "Swaru@55396");

         int var1;
         do {
            displayMenu();
            System.out.print("Enter your choice: ");
            var1 = scanner.nextInt();
            scanner.nextLine();
            switch (var1) {
               case 1:
                  registerStudent();
                  break;
               case 2:
                  loginStudent();
                  break;
               case 3:
                  enterMarks();
                  break;
               case 4:
                  showStudentAverage();
                  break;
               case 5:
                  showTopper();
                  break;
               case 6:
                  System.out.println("Exiting program. Goodbye! \ud83d\udc4b");
                  break;
               default:
                  System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
         } while(var1 != 6);
      } catch (SQLException var12) {
         System.err.println("Database connection failed: " + var12.getMessage());
      } catch (ClassNotFoundException var13) {
         System.err.println("MySQL Driver not found: " + var13.getMessage());
      } finally {
         try {
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException var11) {
            System.err.println("Error closing connection: " + var11.getMessage());
         }

         scanner.close();
      }

   }

   private static void displayMenu() {
      System.out.println("--- Student Result System ---");
      System.out.println("1. Register Student");
      System.out.println("2. Login Student");
      System.out.println("3. Enter Marks");
      System.out.println("4. Show Student Average");
      System.out.println("5. Show Topper");
      System.out.println("6. Exit");
      System.out.println("-----------------------------");
   }

   private static void registerStudent() throws SQLException {
      System.out.print("Enter username: ");
      String var0 = scanner.nextLine();
      System.out.print("Enter password: ");
      String var1 = scanner.nextLine();
      String var2 = "INSERT INTO students (username, password) VALUES (?, ?)";

      try {
         PreparedStatement var3 = conn.prepareStatement(var2);

         try {
            var3.setString(1, var0);
            var3.setString(2, var1);
            int var4 = var3.executeUpdate();
            if (var4 > 0) {
               System.out.println("Registration successful! \ud83c\udf89");
            }
         } catch (Throwable var7) {
            if (var3 != null) {
               try {
                  var3.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (var3 != null) {
            var3.close();
         }
      } catch (SQLIntegrityConstraintViolationException var8) {
         System.out.println("Username already exists. Please choose another.");
      }

   }

   private static void loginStudent() throws SQLException {
      System.out.print("Enter username: ");
      String var0 = scanner.nextLine();
      System.out.print("Enter password: ");
      String var1 = scanner.nextLine();
      String var2 = "SELECT id FROM students WHERE username = ? AND password = ?";
      PreparedStatement var3 = conn.prepareStatement(var2);

      try {
         var3.setString(1, var0);
         var3.setString(2, var1);
         ResultSet var4 = var3.executeQuery();
         if (var4.next()) {
            loggedInStudentId = var4.getInt("id");
            System.out.println("Login successful! Welcome, " + var0 + ".");
         } else {
            System.out.println("Invalid username or password.");
            loggedInStudentId = -1;
         }
      } catch (Throwable var7) {
         if (var3 != null) {
            try {
               var3.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (var3 != null) {
         var3.close();
      }

   }

   private static void enterMarks() throws SQLException {
      if (loggedInStudentId == -1) {
         System.out.println("Please login first to enter marks.");
      } else {
         System.out.print("Enter subject name: ");
         String var0 = scanner.nextLine();
         System.out.print("Enter score: ");
         int var1 = scanner.nextInt();
         scanner.nextLine();
         String var2 = "INSERT INTO marks (student_id, subject, score) VALUES (?, ?, ?)";
         PreparedStatement var3 = conn.prepareStatement(var2);

         try {
            var3.setInt(1, loggedInStudentId);
            var3.setString(2, var0);
            var3.setInt(3, var1);
            var3.executeUpdate();
            System.out.println("Marks entered successfully! \ud83d\udc4d");
         } catch (Throwable var7) {
            if (var3 != null) {
               try {
                  var3.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (var3 != null) {
            var3.close();
         }

      }
   }

   private static void showStudentAverage() throws SQLException {
      if (loggedInStudentId == -1) {
         System.out.println("Please login first to view your average.");
      } else {
         String var0 = "SELECT AVG(score) AS average FROM marks WHERE student_id = ?";
         PreparedStatement var1 = conn.prepareStatement(var0);

         try {
            var1.setInt(1, loggedInStudentId);
            ResultSet var2 = var1.executeQuery();
            if (var2.next() && var2.getString("average") != null) {
               double var3 = var2.getDouble("average");
               System.out.printf("Your average score is: %.2f\n", var3);
            } else {
               System.out.println("No marks found for this student.");
            }
         } catch (Throwable var6) {
            if (var1 != null) {
               try {
                  var1.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (var1 != null) {
            var1.close();
         }

      }
   }

   private static void showTopper() throws SQLException {
      String var0 = "SELECT s.username, AVG(m.score) AS average FROM marks m JOIN students s ON m.student_id = s.id GROUP BY s.username ORDER BY average DESC LIMIT 1";
      Statement var1 = conn.createStatement();

      try {
         ResultSet var2 = var1.executeQuery(var0);

         try {
            if (var2.next()) {
               String var3 = var2.getString("username");
               double var4 = var2.getDouble("average");
               System.out.printf("\ud83d\udc51 The Topper is **%s** with an average score of %.2f\n", var3, var4);
            } else {
               System.out.println("No marks have been entered yet to determine a topper.");
            }
         } catch (Throwable var8) {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (var2 != null) {
            var2.close();
         }
      } catch (Throwable var9) {
         if (var1 != null) {
            try {
               var1.close();
            } catch (Throwable var6) {
               var9.addSuppressed(var6);
            }
         }

         throw var9;
      }

      if (var1 != null) {
         var1.close();
      }

   }

   static {
      scanner = new Scanner(System.in);
      conn = null;
   }
}
