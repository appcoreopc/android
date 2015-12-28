using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Runtime.Serialization;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace ApiTest.Controllers
{
    public class TestController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<Employee> Get()
        {

            System.IO.File.AppendAllText("c:\\temp\\test.log", " GET " + DateTime.Now.ToString() + "\n");

            var l = new List<Employee>();
            l.Add(new Employee("Jeremy1", "kepung@gmail.com"));
            l.Add(new Employee("Jeremy2", "theedgeB@gmail.com"));
            return l;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {

        }

        [HttpPost]
        public HttpResponseMessage Upload()
        {
            var httpRequest = HttpContext.Current.Request;
            if (httpRequest.Files.Count > 0)
            {
                foreach (string file in httpRequest.Files)
                {
                    var postedFile = httpRequest.Files[file];

                    var name = System.IO.Path.GetFileNameWithoutExtension(postedFile.FileName);
                    var ext = System.IO.Path.GetExtension(postedFile.FileName);
                    var filePath = HttpContext.Current.Server.MapPath("~/" + name + "_" + DateTime.Now.ToFileTime() + ext);
                    postedFile.SaveAs(filePath);
                }
                
                return Request.CreateResponse(HttpStatusCode.Created);
            }

            return Request.CreateResponse(HttpStatusCode.BadRequest);
        }
    }

    [Serializable]
    [DataContract]
    public class Employee
    {
        
        public Employee(string n, string e)
        {
            Name = n;
            Email = e;
        }
        [DataMember]
        public string Name { get; set; }

        [DataMember]
        public string Email { get; set; }
    }
}