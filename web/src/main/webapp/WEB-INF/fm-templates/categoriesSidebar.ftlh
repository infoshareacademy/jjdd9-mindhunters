<div class="d-none d-md-block">

    <div class="list-group  list-element sticky-scroll sidebar-list p-0 "  id="ID01" >

        <#list categories as category>
            <a class="list-group-item sidebar-list "
                    <#if queryName!?contains((category.id)?string)>
                        style="background-color: #1b1e21"
                    </#if>
               >
                ${category.name}
                <p hidden > ${category.id} </p>

            </a>

        </#list>


    </div>

    <div class="list-group  list-element  sidebar-list p-0 "  id="ID02" >
        <a class="list-group-item sidebar-list ">Alcoholic</a>
        <a class="list-group-item sidebar-list ">Non alcoholic</a>
        <a class="list-group-item sidebar-list ">Optional alcohol</a>
    </div>

</div>
