## get all ec2 instances
ec2() {
	aws ec2 describe-instances --query 'Reservations[].Instances[].[Tags[?Key==`Name`]|[0].Value,InstanceId,InstanceType,State.Name,PublicIpAddress,PrivateIpAddress]' --output table --profile "$1";
}

## check target group health
ah() {
	for arn in "$(aws elbv2 describe-target-groups --query "TargetGroups[*].TargetGroupArn" --output text --profile $1)"
	do
		echo "$arn"
		aws elbv2 describe-target-health --target-group-arn "$arn" --query "TargetHealthDescriptions[*].[Target.Id,TargetHealth.State]";
	done
}
