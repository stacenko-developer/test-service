namespace BlazorClient.Dto;

public class UserLoginDto
{
    public string Login { get; set; } = string.Empty;
    public string Password { get; set; } = string.Empty;
    public int UserOffsetSecond { get; set; } = 14400;
}
