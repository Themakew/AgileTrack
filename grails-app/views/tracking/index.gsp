<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tracking.label', default: 'Tracking')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <a href="http://designshack.net/" class="button">Click Me</a>
        <a href="#list-tracking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <!-- <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div> -->
        <div>
          Hello
          ${result}
          <g:each var="row" in="${result}">
              ${result}
          </g:each>
        </div>


        <!-- <div id="list-tracking" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${trackingList}" />

            <div class="pagination">
                <g:paginate total="${trackingCount ?: 0}" />
            </div>
        </div> -->
    </body>
</html>
