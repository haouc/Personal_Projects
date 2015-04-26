//
//  MasterViewController.m
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "MasterViewController.h"
#import "DetailViewController.h"

@interface MasterViewController ()

//@property NSMutableArray *objects;
@end

@implementation MasterViewController

- (void)awakeFromNib {
    [super awakeFromNib];
    self.clearsSelectionOnViewWillAppear = NO;
    self.preferredContentSize = CGSizeMake(320.0, 600.0);
    
}

- (void)downloadData {
    NSString* url = @"http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=8&q=http%3A%2F%2Fnews.google.com%2Fnews%3Foutput%3Drss";
    [[SharedNetworking sharedNetworking] getFeedForURL:url
                                                             success:^(NSMutableDictionary *dictionary, NSError *error) {
                                                                 self.data = dictionary[@"responseData"][@"feed"][@"entries"];
                                                                 // Use dispatch_async to update the table on the main thread
                                                                 dispatch_async(dispatch_get_main_queue(), ^{
                                                                     [self.tableView reloadData];
                                                                     
                                                                     //if the call was done from UIRefreshControl, stop the spinner
                                                                     if (self.refreshControl.refreshing) {
                                                                         [self.refreshControl endRefreshing];
                                                                     }
                                                                 });
                                                             }
                                                             failure:^{
                                                                 dispatch_async(dispatch_get_main_queue(), ^{
                                                                     NSLog(@"Problem with Data");
                                                                 });
                                                             }];
}

- (void)viewDidLoad {
    [super viewDidLoad];

//    if ([[SharedNetworking sharedNetworking] networkIsActive]){
//        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Alert!" message:@"No network available. Please make sure your device is not on airplane mode." delegate:self cancelButtonTitle:@"ok" otherButtonTitles:nil];
//        [alert show];
//    }


    self.detailViewController = (DetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
    NSString* url = @"http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=8&q=http%3A%2F%2Fnews.google.com%2Fnews%3Foutput%3Drss";
    [[SharedNetworking sharedNetworking] getFeedForURL:url success:^(NSDictionary *dictionary, NSError *error){
        self.links = dictionary[@"responseData"][@"feed"][@"entries"];
//        for (NSDictionary *link in self.links) {
//            NSLog(@"DownloadedDate:%@\n%@\n%@\n%@",
//                  link[@"link"],
//                  link[@"contentSnippet"],
//                  link[@"publishedDate"],
//                  link[@"title"]);
//        }
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.tableView reloadData];
        });
    }failure:^{
        dispatch_async(dispatch_get_main_queue(), ^{
            NSLog(@"Problem with first view Data");
        });
    }];
    

    
    [self downloadData];
    self.refreshControl = [[UIRefreshControl alloc] init];
    self.refreshControl.tintColor = [UIColor redColor];
    [self.refreshControl addTarget:self action:@selector(refreshTable:) forControlEvents:UIControlEventValueChanged];
    

}


#pragma refreshTable
- (void)refreshTable:(UIRefreshControl *)refreshControl{
    [self downloadData];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Segues

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if ([[segue identifier] isEqualToString:@"showDetail"]) {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
//        NSDate *object = self.objects[indexPath.row];
        NSDictionary *link = [self.data objectAtIndex:indexPath.row];

        _firstLink = [[self.data objectAtIndex:0] objectForKey:@"link"];
        
        DetailViewController *controller = (DetailViewController *)[[segue destinationViewController] topViewController];
        [controller setLinkItem:link];
        controller.navigationItem.leftBarButtonItem = self.splitViewController.displayModeButtonItem;
        controller.navigationItem.leftItemsSupplementBackButton = YES;
       
    }
}

#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.data count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ListTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ListCell" forIndexPath:indexPath];
    NSMutableDictionary *newsLink = [self.data objectAtIndex:indexPath.row];
    
//    NSLog(@"%@", newsLink);
    
    cell.newsTitle.text = [newsLink objectForKey:@"title"];
    cell.newsSnippet.text = [newsLink objectForKey:@"contentSnippet"];

    NSDateFormatter *formatter = [[NSDateFormatter alloc] init] ;
    NSString *dateString = [newsLink objectForKey:@"publishedDate"];
    [formatter setDateFormat:@"EEE, dd MMM yyyy HH:mm:ss Z"];
    NSDate *date = [[NSDate alloc] init];
    date = [formatter dateFromString:dateString];
    [formatter setDateFormat:@"MM/dd/yyyy"];
    cell.newsDate.text = [formatter stringFromDate:date];
    return cell;
}




@end
