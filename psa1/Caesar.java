// Keep these two lines.  They are what tell Java to include the
// classes you need for working with files.
// You might get warnings about them at first.  That's OK, just
// ignore the warnings.  They should go away as you complete your code.
import java.io.*;
import java.util.*;

/**File Header:
  * This file makes multiple methods that encrypt and
  * decrypt messages. Two of these methods utilize String
  * to achieve this, while two other methods utilize
  * StringBuilder to achieve this. The fifth method is used
  * by the other methods to change the characters.
  * 
  * Mark Choe
  * A13917840
  * mychoe@ucsd.edu
  * 01/12/18
  * 
  * Class Header: 
  * The purpose of this class is to encrypt and decrypt messages
  * different ways. Some methods use solely String, while others 
  * use StringBuilder.
  * */

public class Caesar {
 // Complete the methods below.  Be sure to add header
 // comments for each. You may (and should) also write additional
 // helper methods.  Be sure to make the helper methods private and include
 // header comments for each.

 // Although you will not be graded on style this week, you should follow
 // these basic style guidelines nonetheless.   You will be graded on this
 // in weeks to come, so start to practice now.

 // Use proper indenting: Indent each block of code (e.g., method body,
 //   loop body.  Line up the lines in the block so that they are all
 //   indented to the same degree.  See examples of this in the book
 //   and in the code below.
 // Use descriptive variable names: The names of your variables should
 //   describe the data they hold.  Almost always, your variable names
 //   should be words (or abbreviations), not single letters.
 // Write short methods: Break your methods up into submethods if they
 //   are getting too complicated or long.  Generally your methods
 //   shouldn't get too much longer than about 20 lines of code (give or take)
 // Write short lines: Each line of code should be no longer than 80
 //   characters, so it can fit in a reasonable size window.  There's a
 //   column number in both vim and emacs.
 //
 // We'll start with these, as these are the most important.  We may add
 // to this list later in the term, but if you do all of the above you're
 // in good shape.
  
  /* This method encrypts the characters in the String by rotating them by 
   * the amount inputted.
   * Input: the String and the amount the characters need to be rotated by
   * Return: the encrypted String
   */
  public static String encrypt(String s, int rotation) {
    // Complete this method
    // letters are between 65 and 90 (Upper Case) and 97 and 122 (Lower Case)
    //If the String is null or is empty
    if (s == null || s.length() == 0){
      return "";
    } else {
      //Making a new empty String
      String nString = new String();
      //for loop to go through the given String
      for (int i = 0; i < s.length(); i++){
        char letter = s.charAt(i);
        //Filling in the empty String
        nString = nString + letterOperation(letter, rotation);
      }
      
      return nString;
    }
  }
  /* This method decrypts the characters in the String by rotating them the
   * opposite way of the amount rotated by the encryption method.
   * Input: the String and the amount the characters need to be rotated by
   * Return: the decrypted String
   */
  public static String decrypt(String s, int rotation) {
    // Complete this method
    //If the String is null or is empty
    if (s == null || s.length() == 0){
      return "";
    } else {
      //Making a new empty String
      String nString = new String();
      //for loop to go through the given String
      for (int i = 0; i < s.length(); i++){
        char letter = s.charAt(i);
        //Filling in the empty String
        nString = nString + letterOperation(letter, -(rotation));
      }
      
      return nString;
    }
  }
  /* This method changes the iputted character by the rotation amount.
   * Input: the character and the amount to be rotated
   * Return: the changed character
   */
  private static char letterOperation(char a, int rotation) {
    //Complete this method
    // Setup that helps address massive rotations
    rotation = rotation % 26;
    //If the character is a capital letter
    if (a < 91 && a > 64){ 
      //If rotating causes the character to go before 'A'
      if (a + rotation < 65){
        a = (char) (a + rotation + (26));
        //If rotating causes the character to go after 'Z'
      } else if (a + rotation > 90){
        a = (char) (a + rotation - (26));
      } else {
        //If rotating does neither of these
        a = (char) (a + rotation);
      }
      //If the character is a lowercase letter
    } else if (a < 123 && a > 96){
      //If rotating causes the character to go before 'a'
      if (a + rotation < 97){
        a = (char) (a + rotation + (26));
        //If rotating causes the character to go after 'z'
      } else if (a + rotation > 123){
        //If rotating doe neither of these
        a = (char) (a + rotation - (26));
      } else {
        a = (char) (a + rotation);
      }
    }
    //Returning the changed character
    return a;
  }
  /* This method encrypts the characters in the String by rotating them by 
   * the amount inputted. Additionally, this method uses StringBuilder.
   * Input: the String and the amount the characters need to be rotated by
   * Return: the encrypted String
   */
  public static String encryptTwo(String s, int rotation) {
    // Complete this method    
    //If the String is null or is empty
    if (s == null || s.length() == 0){
      return "";
    } else {
      //Making a new empty String
      String nString = new String();
      //Making a new empty StringBuilder
      StringBuilder stringB = new StringBuilder();
      //for loop to go through the given String
      for (int i = 0; i < s.length(); i++){
        char letter = s.charAt(i);
        //Filling in the the StringBuilder
        stringB = stringB.append(letterOperation(letter, rotation));
      }
      //Filling in the empty String
      nString = nString + stringB;
      
      return nString;
    }
  }
  /* This method decrypts the characters in the String by rotating them the
   * opposite way of the amount rotated by the encryption method. Additionally,
   * this method utilizes StringBuilder.
   * Input: the String and the amount the characters need to be rotated by
   * Return: the decrypted String
   */
  public static String decryptTwo(String s, int rotation) {
    // Complete this method
    //If the String is null or is empty
    if (s == null || s.length() == 0){
      return "";
    } else {
      //Making a new empty String
      String nString = new String();
      //Making a new empty StringBuilder
      StringBuilder stringB = new StringBuilder();
      //for loop to go through the given String
      for (int i = 0; i < s.length(); i++){
        char letter = s.charAt(i);
        //Filling in the the StringBuilder
        stringB = stringB.append(letterOperation(letter, -(rotation)));
      }
      //Filling in the empty String
      nString = nString + stringB;
      
      return nString;
    }
  }
}
