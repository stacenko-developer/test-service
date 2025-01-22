using System.Text.Json.Serialization;

namespace BlazorClient.Dto.LocalStorage;

/// <summary>
/// Класс для парсинга данных JWT-токена
/// в локальном хранилище браузера
/// </summary>
public class LocalStorageTokenData
{
    [JsonPropertyName("errCode")]
    public string ErrCode { get; set; } = string.Empty;

    [JsonPropertyName("content")]
    public Content Content { get; set; } = new();
}