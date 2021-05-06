package hangman;
import java.util.*;
import java.io.*;

public class hangman 
{
    public void playhangman() throws FileNotFoundException
    {
            Scanner keyboard= new Scanner(System.in);                           //to read the user input
            List<String> words= new ArrayList<>();                              //list of the words from the file
            List<Character> guessed_char= new ArrayList<>();                    //list of user guesses

            Random rand = new Random();
            File[] categories = new File( "D:/Java/bin/hangman/category" ).listFiles();
            File genre = categories[ rand.nextInt( categories.length ) ];
            Scanner in=new Scanner(genre);                                     //Scanner in= new Scanner(new File("wordslist.txt"));  
            
            while(in.hasNext())
            {
                words.add(in.nextLine());
            }
            in.close();

            Random r= new Random();
            String word_to_be_guessed= words.get(r.nextInt(words.size()));

            int wrongcount=0;
            while(true)
            {
                
                String category=genre.getName();
                category = category.substring(0, category.lastIndexOf("."));
                System.out.println("CATEGORY: " +category+"\n\n");

                hangmanstatus(wrongcount);                                  //draws the hangman

                if(wrongcount>=6)
                {
                    System.out.println("\nYou lose");
                    System.out.println("\nCorrect word was " +word_to_be_guessed);
                    break;
                }

                currentstate(word_to_be_guessed, guessed_char);
                if(!getletter(keyboard, guessed_char, word_to_be_guessed))
                {
                    wrongcount++;
                }

                if(currentstate(word_to_be_guessed, guessed_char))
                {
                    break;
                }

                System.out.println("\nEnter guess for the word:");
                if(keyboard.nextLine().equals(word_to_be_guessed))
                {
                    System.out.println("\nYou Win!!!");
                    break;
                }

                else
                {
                    System.out.println("\nWrong!!!Try again");
                }
            }

        }

        private static void hangmanstatus(int wrongcount) {
            System.out.println("---------------");
            System.out.println("|             |");

            if(wrongcount>=1)
            {
                System.out.println(" O");
            }

            if(wrongcount>=2)
            {
                System.out.print("\\ ");
            }

            if(wrongcount>=3)
            {
                System.out.println("/");
            }
            else
            {
                System.out.println("");
            }

            if(wrongcount>=4)
            {
                System.out.println(" |");
            }

            if(wrongcount>=5)
            {
                System.out.print("/ ");
            }
            
            if(wrongcount>=6)
            {
                System.out.println("\\");
            }
            else
            {
                System.out.println("");
            }
        }

        private static boolean getletter (Scanner keyboard, List<Character> guessed_char, String word_to_be_guessed) 
        {
            System.out.println("Enter a letter: ");
            String letterguess=keyboard.nextLine();                                     //User's guess
            guessed_char.add(letterguess.charAt(0));

            return word_to_be_guessed.contains(letterguess);
        }

        
        private static boolean currentstate(String word_to_be_guessed, List<Character> guessed_char) 
        {
            int correctcount=0;
            for(int i=0;i<word_to_be_guessed.length();i++)
            {
                if(guessed_char.contains(word_to_be_guessed.charAt(i)))
                {
                    System.out.print(word_to_be_guessed.charAt(i));
                    correctcount++;
                }

                else
                {
                    System.out.print(" _ ");
                }
            }
            System.out.println();
            return(word_to_be_guessed.length()==correctcount);          //returns true if complete word is guessed, else returns false
        }
}
