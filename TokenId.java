class TokenId
{
  private int number;
  private String ID;

  public void SetNumber(int input)
  {
    number = input;
  }

  public void SetID(String input)
  {
    ID = input;
  }

  public int GetNumber()
  {
    return number;
  }

  public String GetID()
  {
    return ID;
  }

  TokenId(int input_number, String input_ID)
  {
    number = input_number;
    ID = input_ID;
  }

  TokenId()
  {
    number = 0;
    ID = "";
  }
}