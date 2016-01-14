using System;
using System.Threading.Tasks;
using Microsoft.Owin;
using Owin;
using Microsoft.Owin.Security.OAuth;
using System.Security.Claims;
using Microsoft.Owin.Security;

[assembly: OwinStartup(typeof(OAuthServer.App_Start.Startup))]

namespace OAuthServer.App_Start
{

    /// <summary>
    /// Generate token if username = 1234 and password = abc
    /// </summary>

    public class Startup
    {
        private const string AuthorizationPath = "/oauth/token";
        public void Configuration(IAppBuilder app)
        {

            var authorizationOption = new OAuthAuthorizationServerOptions
            {
                TokenEndpointPath = new PathString(AuthorizationPath),
                Provider = new MyAuthorizationServerProvider(),
                AccessTokenExpireTimeSpan = TimeSpan.FromMinutes(30),
                AllowInsecureHttp = true,
                AuthenticationMode = Microsoft.Owin.Security.AuthenticationMode.Active
            };

            //app.UseOAuthAuthorizationServer(new OAuthAuthorizationServerOptions
            //{
            //    TokenEndpointPath = new PathString(AuthorizationPath),
            //    Provider = new MyAuthorizationServerProvider(),
            //    AccessTokenExpireTimeSpan = TimeSpan.FromMinutes(30),
            //    AllowInsecureHttp = true,
            //    AuthenticationMode = Microsoft.Owin.Security.AuthenticationMode.Active
            //});


            app.UseOAuthBearerTokens(authorizationOption); 
        }
    }

    public class MyAuthorizationServerProvider : OAuthAuthorizationServerProvider
    {
        public override Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            string clientId = string.Empty;
            string clientPassword = string.Empty;

            if (context.TryGetFormCredentials(out clientId, out clientPassword))
            {
                if (clientId == "1234" && clientPassword == "abc")
                {
                    Client fakeClient = new Client();
                    fakeClient.Name = "Jeremy";
                    fakeClient.Id = "kepung";
                    fakeClient.AllowedGrant = OAuthGrant.Client;
                    fakeClient.ClientSecretHash = "lalala";

                    context.OwinContext.Set("oauth:client", fakeClient);
                    context.Validated(clientId);
                }
                else
                {
                    context.Rejected();
                }
            }

            return base.ValidateClientAuthentication(context);
        }

        public override Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            Client client = context.OwinContext.Get<Client>("oauth:client");

            if (client.AllowedGrant == OAuthGrant.Client)
            {
                // Ok // 
                var oAuthIdentity = new ClaimsIdentity(context.Options.AuthenticationType);
                oAuthIdentity.AddClaim(new Claim(ClaimTypes.Name, "jeremy"));
                var ticket = new AuthenticationTicket(oAuthIdentity, new AuthenticationProperties());
                //ClaimsIdentity identity = new ClaimsIdentity("OAuth", "name", "admin");
                context.Validated(ticket);
            }
            else if (client.AllowedGrant == OAuthGrant.ResourceOwner)
            {
                // Checks for resource access
            }
            else
            {
                context.Rejected();
            }
            return base.GrantResourceOwnerCredentials(context);
        }
    }

    public class Client
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string ClientSecretHash { get; set; }
        public OAuthGrant AllowedGrant { get; set; }
        public DateTimeOffset CreatedOn { get; set; }
    }
}
