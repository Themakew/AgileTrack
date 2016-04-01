<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tracking.label', default: 'Tracking')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-acceptanceCriteria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Register Requirements<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="/investimentTheme/index" style="color:white">Add Investiment Theme</a>
                    </li>
                    <li>
                        <a href="/epic/index" style="color:white">Add Epic</a>
                    </li>
                    <li>
                        <a href="/features/index" style="color:white">Add Features</a>
                    </li>
                    <li>
                        <a href="/sprint/index" style="color:white">Add Sprint</a>
                    </li>
                    <li>
                        <a href="/userStory/index" style="color:white">Add User Story</a>
                    </li>
                    <li>
                        <a href="/task/index" style="color:white">Add Task</a>
                    </li>
                    <li>
                        <a href="/acceptanceCriteria/index" style="color:white">Add Acceptance Criteria</a>
                    </li>
                </ul>
            </li>
        </div>

        <g:form name="testForm" controller="tracking" action="gettextfield">
                    <g:textField name="textoRecebido" value="${textoRecebido}"> </g:textField>
                    <g:submitButton name="Submit" value="Submit"></g:submitButton>
                </g:form>

        <a href="#list-tracking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <!-- <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div> -->
        <div>
          <g:each var="row" in="${tracking}">
            <table>
              <tr>
                <th>
                  <a href="/investimentTheme/show/${row.theme_id}">Thema ${row.theme_initials} - ${row.theme_id}</a>
                </th>
                <td>
                  <table>
                    <tr>
                      <th>
                        <a href="/epic/show/${row.epic_id}">Epic ${row.epic_initials} - ${row.epic_id}</a>
                      </th>
                      <td>
                        <table>
                          <tr>
                            <th>
                              <a href="/features/show/${row.feature_id}">Feature ${row.feature_initials} - ${row.feature_id}</a>
                            </th>
                            <td>
                              <table>
                                <th>
                                  <a href="/userStory/show/${row.user_story_id}"> User Story ${row.user_story_initials} - ${row.user_story_id}<br>
                                  Pontuation: ${row.user_story_pontuation} </a> <br>
                                  <a href="/sprint/show/${row.sprint_id}"> Sprint : ${row.sprint_num} </a>
                                </th>
                                <td>
                                  <table>
                                    <th>
                                      <a href="/task/show/${row.task_id}"> Task ${row.task_initials} - ${row.task_id} </a> <br>
                                    </th>
                                    <td>
                                      <table>

                                        <th>
                                          <g:if test="${row.acceptance_criteria_isAccepted}">
                                            <a style="color:green" href="/acceptanceCriteria/show/${row.acceptance_criteria_id}">Acceptance Criteria ${row.acceptance_criteria_initials} - ${row.acceptance_criteria_id} </a>
                                          </g:if>
                                          <g:else>
                                            <a style="color:red" href="/acceptanceCriteria/show/${row.acceptance_criteria_id}">Acceptance Criteria ${row.acceptance_criteria_initials} - ${row.acceptance_criteria_id} </a>                                          </g:else>
                                        </th>
                                      </table>
                                    </td>
                                  </table>
                                </td>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
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
