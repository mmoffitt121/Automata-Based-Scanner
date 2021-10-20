class Relation
{
  private int startstate;
  private String condition;
  private int endstate;

  public void SetStartState(int input)
  {
    startstate = input;
  }

  public void SetCondition(String input)
  {
    condition = input;
  }

  public void SetEndState(int input)
  {
    endstate = input;
  }

  public int GetStartState()
  {
    return startstate;
  }

  public String GetCondition()
  {
    return condition;
  }

  public int GetEndState()
  {
    return endstate;
  }

  Relation(int in_startstate, String in_condition, int in_endstate)
  {
    SetStartState(in_startstate);
    SetCondition(in_condition);
    SetEndState(in_endstate);
  }

  Relation()
  {
    SetStartState(0);
    SetCondition("");
    SetEndState(0);
  }
}