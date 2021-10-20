import java.util.ArrayList;
import java.util.Arrays;

class Automata
{
  private ArrayList<String> possibletokens;
  private ArrayList<Integer> states;
  private ArrayList<Integer> start;
  private ArrayList<Integer> terminal;
  private ArrayList<Relation> relations;
  private ArrayList<TokenId> ids;

  public ArrayList<String> GetTokens()
  {
    return possibletokens;
  }

  public String GetTokenString()
  {
    return "[" + String.join("", possibletokens) + "]";
  }

  public ArrayList<Integer> GetStates()
  {
    return states;
  }

  public int GetStart()
  {
    return start.get(0);
  }

  public ArrayList<Integer> GetTerminal()
  {
    return terminal;
  }

  public ArrayList<Relation> GetRelations()
  {
    return relations;
  }

  public ArrayList<TokenId> GetTokenIDs()
  {
    return ids;
  }

  public void PrintAutomata()
  {
    System.out.print("\n-=-= {Automata} =-=-\n");
    System.out.println(Arrays.toString(possibletokens.toArray()));
    System.out.println(Arrays.toString(states.toArray()));
    System.out.println(Arrays.toString(start.toArray()));
    System.out.println(Arrays.toString(terminal.toArray()));
    for (Relation i : relations)
    {
      System.out.print("(" + i.GetStartState() + ", " + i.GetCondition() + ", " + i.GetEndState() + ") ");
    }
    System.out.print("\n");
    for (TokenId i : ids)
    {
      System.out.print("(" + i.GetNumber() + ", " + i.GetID() + ") ");
    }
    System.out.print("\n-=-=------------=-=-\n");
  }

  Automata(ArrayList<String> in_possibletokens, ArrayList<Integer> in_states, ArrayList<Integer> in_start, ArrayList<Integer> in_terminal, ArrayList<Relation> in_relations, ArrayList<TokenId> in_ids)
  {
    possibletokens = in_possibletokens;
    states = in_states;
    start = in_start;
    terminal = in_terminal;
    relations = in_relations;
    ids = in_ids;
  }
}