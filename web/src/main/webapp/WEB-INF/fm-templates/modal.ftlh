<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are You more then 18 years old? If not you will not be allowed to see details of alcoholic beverages.
            </div>
            <div class="modal-footer">

                <form action="/list" method="GET">
                    <input type="hidden" value="true"
                           name="adult"/>
                    <input type="hidden" value="1"
                           name="page"/>

                    <button class="btn btn-primary" type="submit">Yes</button>
                </form>


                <form action="/list" method="GET">

                    <input type="hidden" value="false"
                           name="adult"/>

                    <input type="hidden" value="1"
                           name="page"/>

                    <button class="btn btn-secondary" type="submit">No</button>
                </form>


            </div>
        </div>
    </div>
</div>
