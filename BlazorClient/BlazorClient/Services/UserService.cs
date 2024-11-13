using BlazorClient.Dto.LocalStorage;
using Blazored.LocalStorage;
using System.IdentityModel.Tokens.Jwt;
using System.Text.Json;

namespace BlazorClient.Services;

public class UserService(ILocalStorageService localStorageService)
{
    public async Task<Guid> GetCurrentUserIdFromJwtToken()
    {
        // Вынести в отдельный метод
        var localStorageTokenDataString = await localStorageService.GetItemAsStringAsync("token") ??
            throw new Exception("Token not found");
        localStorageTokenDataString = localStorageTokenDataString.Replace(@"\u0022", "\"").Remove(0, 1);
        localStorageTokenDataString = localStorageTokenDataString.Remove(localStorageTokenDataString.Length - 1, 1);
        //

        Console.WriteLine(localStorageTokenDataString);
        var localStorageTokenData = JsonSerializer.Deserialize<LocalStorageTokenData>(localStorageTokenDataString);
        Console.WriteLine(localStorageTokenData is null);
        Console.WriteLine(localStorageTokenData.ErrCode);
        Console.WriteLine(localStorageTokenData.Content.Token);
        var token = localStorageTokenData.Content.Token;


        var handler = new JwtSecurityTokenHandler();
        var tokenData = handler.ReadJwtToken(token);
        Console.WriteLine(tokenData.Payload.Count);

        return Guid.NewGuid();
    }
}
