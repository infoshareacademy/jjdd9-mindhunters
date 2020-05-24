<footer>
    <div class="bottom-ft" id="bottom-ft">
        <div class="container container-fluid">
            <div class="row icons-row">
                <div class="col-sm-2">
                    <span class="fab fa-github text-warning"> github</span>
                </div>
                <div class="col-sm-2">
                    <span class="fab fa-facebook-square text-warning"> facebook</span>
                </div>
                <div class="col-sm-2">
                    <span class="fab fa-twitter-square text-warning"> twitter</span>
                </div>
                <div class="col-sm-2">
                    <span class="fab fa-linkedin text-warning"> linkedin</span>
                </div>
                <div class="col-sm-2">
                    <span class="fab fa-instagram text-warning"> insta</span>
                </div>
                <div class="col-sm-2">
                    <a class="btn far fa-caret-square-up js-scroll-trigger text-warning" href="#part1-top"> back</a>2
                </div>
            </div>
            <div class="row-copy">
                <i style="color: white; text-align: center">
                    <#assign aDateTime = .now>
                    <#assign aDate = aDateTime?date>
                    Copyright &copy; all reserved ${aDate?string.yyyy}.
                </i>
            </div>
        </div>
    </div>
</footer>