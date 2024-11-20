using System.Text.Json.Serialization;

namespace BlazorClient.Dto.ApiIntegration;

/// <summary>
/// Класс для репрезентации ответа от апи-сервера
/// </summary>
public class ApiResponse
{
    [JsonPropertyName("errCode")]
    public string ErrCode { get; set; } = string.Empty;
    [JsonPropertyName("content")]
    public string Content { get; set; } = string.Empty;
}
