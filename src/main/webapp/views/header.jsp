 <nav class="navbar navbar-inverse">
 
 
        <div class="container">
  <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">

                <a class="navbar-brand" href="#">My Footy Blog</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            
            <div class="collapse navbar-collapse" id="navbar-collapse-4">
                <ul class="nav navbar-nav navbar-right">
                <li><a href="/">Home</a></li>
                 <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/person">Manage Users</a></li>
                    </sec:authorize>
                    
                  
                      
                    
                     <sec:authorize access="hasRole('ROLE_USER')">
               <li>      <a href="addPost">New Post</a></li>
                < <li><a href="/editPerson">${currUser}</a></li>
               <li>      <a href="/logout">Logout</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">  <li><a href="/login">Login</a></li></sec:authorize>
                     <sec:authorize access="isAnonymous()">  <li><a href="/register">Register</a></li></sec:authorize>
                    
                    <!-- <li><a href="#">Works</a></li>
                    <li><a href="#">News</a></li>
                    <li><a href="#">Contact</a></li>
                    <li>
                      <a class="btn btn-default btn-outline btn-circle"  data-toggle="collapse" href="#nav-collapse4" aria-expanded="false" aria-controls="nav-collapse4">Profile <i class=""></i> </a>
                    </li> -->
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->