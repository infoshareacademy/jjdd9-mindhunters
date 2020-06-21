<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Age verification</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are You more then 18 years old? If not you will not be allowed to see details of alcoholic
                beverages.
            </div>
            <div class="modal-footer">

                <form action="/search" method="GET">
                    <input type="hidden" value="true"
                           name="adult"/>
                    <input type="hidden" value="1"
                           name="page"/>

                    <input type="checkbox" name="age18" value="true">
                    <label for="age18">Keep my choice 24h</label>

                    <button class="btn btn-secondary" type="submit">Yes</button>
                </form>

                <form action="/search" method="GET">

                    <input type="hidden" value="false"
                           name="adult"/>

                    <input type="hidden" value="1"
                           name="page"/>

                    <input type="checkbox" name="age18" value="false">
                    <label for="age18">Keep my choice 24h</label>

                    <button class="btn btn-secondary" type="submit">No</button>
                </form>

            </div>
        </div>
    </div>
</div>