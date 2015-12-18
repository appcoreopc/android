using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Runtime.Serialization;
using System.Web.Http;

namespace ApiTest.Controllers
{
    public class TestController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<Employee> Get()
        {
            var l = new List<Employee>();
            l.Add(new Employee("Jeremy", "kepung@gmail.com"));
            l.Add(new Employee("Jeremy", "kepung@gmail.com"));
            return l;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        public void Post([FromBody]string value)
        {
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
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