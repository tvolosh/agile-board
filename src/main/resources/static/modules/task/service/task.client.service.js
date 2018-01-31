angular.module('task').factory('taskService',['$http',
    function($http){
		return{
			fetchAll:function(){
				return $http.get('api/tasks').then(function(response){
					return response;
				});
			},
			createTask : function(task){
				return $http.post("api/tasks",task).then(function(response){
					return response;
				});
			},
			saveTask : function(task){
				return $http.put("api/tasks/"+task.id,task).then(function(response){
					return response;
				});
			},
			deleteTask : function(task){
				return $http.delete("api/tasks/"+task.id).then(function(response){
					return response;
				});
			}
		}
	}
]);