namespace BlazorClient.Dto.Lessons;

/// <summary>
/// Dto урока
/// </summary>
public class LessonDto
{
    /// <summary>
    /// Идентификатор урока
    /// </summary>
    public Guid Id { get; set; }

    /// <summary>
    /// Навзание урока
    /// </summary>
    public string Name { get; set; } = string.Empty;

    /// <summary>
    /// Описание урока
    /// </summary>
    public string Description { get; set; } = string.Empty;
}