Inventory Enhancement
======================

We have an inventory service that had a lot of hard-coded behavior before someone was able to give us an inventory list. We FINALLY got the the inventory list!

We need to load it in and make updates to the application so that we are checking product availability based on this data feed.

For store availability:
* Make sure that nearby stores (see LocationService to determine nearby stores) have a minimum threshold of inventory. We don't want customers to drive to a store that has 1 item in stock that is sold while the person is driving.
* For online, any availability is acceptable and any location.


1. Update the InventoryService so that the availability checks are using the feed.
2. Add the ability for us to get dynamic data (instead of us loading it just once). Spring supports schedules, see: https://spring.io/guides/gs/scheduling-tasks/
3. Add unit tests necessary to ensure that this service is working when others make changes.