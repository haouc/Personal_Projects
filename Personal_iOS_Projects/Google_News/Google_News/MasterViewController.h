//
//  MasterViewController.h
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ListTableViewCell.h"
#import "SharedNetworking.h"


@class DetailViewController;

@interface MasterViewController : UITableViewController

@property (strong, nonatomic) DetailViewController *detailViewController;
@property (strong, nonatomic) UIRefreshControl *refreshControl;
@property (strong, nonatomic) NSMutableArray *data;
@property NSMutableDictionary *links;
//@property NSDictionary *firstLink;
@property NSString *firstLink;



@end

