<div class="d-none d-md-block">

    <div class="list-group  list-element sticky-scroll sidebar-list p-0 " id="myID2"   >

        <#list categories as category>

            <a class="list-group-item sidebar-list"
                    <#if queryName!?contains(category)>
                        style="background-color: #1b1e21"
                    </#if>
            >
                ${category}
            </a>

        </#list>
    </div>
</div>
