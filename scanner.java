import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class scanner
{
  public static void main(String[] args) 
  {
    if (args.length == 0)
    {
      System.out.println("No filename given. Please enter a valid filename.");
    }
    else if (args.length == 1)
    {
      System.out.println("No automata file given. Please enter a valid automata file.");
    }
    else
    {
      try
      {
        // Initialize Scanners
        File file = new File(args[0]);
        Scanner program_scanner = new Scanner(file);
        program_scanner.useDelimiter("");

        File automata_file = new File(args[1]);
        Scanner automata_scanner = new Scanner(automata_file);
        automata_scanner.useDelimiter("[(){}, ]+");
        // xxxxxxxxxxxxxxxxxxx

        // Get Automata xxxxxx
        ArrayList<String> automata_possibletokens = ScanAutomata(automata_scanner);
        ArrayList<Integer> automata_states = ScanAutomataIntegers(automata_scanner);
        ArrayList<Integer> automata_start = ScanAutomataIntegers(automata_scanner);
        ArrayList<Integer> automata_terminal = ScanAutomataIntegers(automata_scanner);
        ArrayList<Relation> automata_relations = ScanRelation(automata_scanner);
        ArrayList<TokenId> automata_IDs = ScanIDs(automata_scanner);
        
        automata_scanner.close();

        Automata automata = new Automata(automata_possibletokens, automata_states, automata_start, automata_terminal, automata_relations, automata_IDs);
        // xxxxxxxxxxxxxxxxxxx

        automata.PrintAutomata();
        ArrayList<String> tokenlist = new ArrayList<String>();
        while (program_scanner.hasNext())
        {
          String toAdd = Scan(program_scanner, automata);
          if (toAdd == null || toAdd.equals("NONE"))
          {
            continue;
          }
          else if (toAdd.equals("ERROR"))
          {
            tokenlist = new ArrayList<String>();
            tokenlist.add(toAdd);
            break;
          }
          tokenlist.add(toAdd);
        }
        
        program_scanner.close();

        System.out.println(Arrays.toString(tokenlist.toArray()));
      }
      catch (FileNotFoundException e)
      {
        System.out.print("File not found.");
      }
    }
  }

  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
  // Scanning
  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

  public static String Scan(Scanner scn, Automata automata)
  {
    int state = automata.GetStart();

    // Case first character exists
    if (scn.hasNext(automata.GetTokenString()))
    {
      while (true)
      {
        if (HasNext(state, automata, scn))  // If has a next possible state, Get the next state.
        {
          state = GetNext(state, scn.next(), automata);
          continue;
        }

        if (IsFinal(state, automata))  // If there is no next possible state, check if the state is a terminal, then return the tojken according to the state.
        {
          return GetTokenFromState(state, automata);
        }
        else  // If there isn't a next possible state, and the program isn't on a terminal, return an error.
        {
          return "ERROR";
        }
      }
    }
    // Case first character is whitespace
    else if (scn.hasNext("[ \n\t]"))
    {
      scn.next();
      return "NONE";
    }
    // Case first character does not exist
    else
    {
      scn.next();
      return "ERROR";
    }
  }

  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
  // Automata Traversal Logic
  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

  public static boolean HasNext(int state, Automata automata, Scanner scn)
  {
    for (Relation i : automata.GetRelations())
    {
      if (state == i.GetStartState() && scn.hasNext(i.GetCondition()))
      {
        return true;
      }
    }
    return false;
  }

  public static boolean IsFinal(int state, Automata automata)
  {
    if (automata.GetTerminal().contains(state))
    {
      return true;
    }
    return false;
  }

  public static int GetNext(int state, String next, Automata automata)
  {
    for (Relation i : automata.GetRelations())
    {
      if (i.GetStartState() == state && next.matches(i.GetCondition()))
      {
        return i.GetEndState();
      }
    }

    System.out.println("Next: " + state + " " +  next);

    return -2147483648;
  }

  public static String GetTokenFromState(int state, Automata automata)
  {
    for (TokenId i : automata.GetTokenIDs())
    {
      if (i.GetNumber() == state)
      {
        return i.GetID();
      }
    }

    return "ERROR";
  }

  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
  // Reading Automata From File
  // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

  public static ArrayList<String> ScanAutomata(Scanner scn)
  {
    ArrayList<String> output = new ArrayList<String>();

    scn.useDelimiter("[{}, ]+");

    while (!scn.hasNext("\n"))
    {
      output.add(scn.next());
    }

    scn.useDelimiter("[(){}, ]+");

    scn.next();

    return output;
  }

  public static ArrayList<Integer> ScanAutomataIntegers(Scanner scn)
  {
    ArrayList<Integer> output = new ArrayList<Integer>();

    while (!scn.hasNext("\n"))
    {
      output.add(Integer.valueOf(scn.next()));
    }

    scn.next();

    return output;
  }

  public static ArrayList<Relation> ScanRelation(Scanner scn)
  {
    ArrayList<Relation> output = new ArrayList<Relation>();

    scn.useDelimiter("");
    scn.next();
    scn.next();
    while (!scn.hasNext("}"))
    {
      scn.useDelimiter("[, ()]+");
      int next1 = Integer.valueOf(scn.next());
      scn.useDelimiter(", ");
      String next2 = scn.next();
      scn.useDelimiter("[, )]+");
      int next3 = Integer.valueOf(scn.next());
      scn.useDelimiter("[, ()\n]");

      output.add(new Relation(next1, next2, next3));
    }

    scn.useDelimiter("[(){}, ]+");

    scn.next();

    return output;
  }

  public static ArrayList<TokenId> ScanIDs(Scanner scn)
  {
    ArrayList<TokenId> output = new ArrayList<TokenId>();

    while (scn.hasNext())
    {
      output.add(new TokenId(Integer.valueOf(scn.next()), scn.next()));
    }

    return output;
  }
}