using BlazorClient.Dto.Role;
using System.Text.Json.Serialization;

namespace BlazorClient.Dto.User;

public class UserDto
{
    [JsonPropertyName("login")]
    public string Login { get; set; } = string.Empty;
    [JsonPropertyName("id")]
    public Guid Id { get; set; }
    [JsonPropertyName("roles")]
    public List<RoleDto> Roles { get; set; } = [];
}
