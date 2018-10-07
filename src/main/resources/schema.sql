DROP procedure IF EXISTS `getOperationsPerMonth`;

DELIMITER $$
USE `autokomis`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getOperationsPerMonth`(dateValue datetime, operationType int)
BEGIN
	select
		case o.type when 1 then 'Zakup' when 2 then 'Sprzedaż' when 3 then 'Użyczenie' end as operationType,
		cast(o.data as char(30)) as operationDate,
        o.value as operationValue,
        concat(c.first_name, ' ', c.name) as customerData,
        concat(v.brand, ' ', v.model, ', (VIN: ', v.vin, ')') as vehicleData,
        o.id as operationId,
        c.id as customerId,
        v.id as vehicleId
	from
		operations o
        join customers c on o.customer_id = c.id
        join vehicles v on o.vehicle_id = v.id
	where
		(o.type = operationType or -1 = operationType) and
        Month(o.data) = Month(dateValue) and
        Year(o.data) = Year(dateValue)
	order by
		o.data;
END$$

DELIMITER ;

