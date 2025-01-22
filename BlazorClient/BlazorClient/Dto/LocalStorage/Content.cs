using System.Text.Json.Serialization;

namespace BlazorClient.Dto.LocalStorage;

/// <summary>
/// Вложенный в LocalStorageTokenData объект Json-а,
/// который лежит в локальном хранилище
/// </summary>
public class Content
{
    [JsonPropertyName("token")]
    public string Token { get; set; } = string.Empty;
}